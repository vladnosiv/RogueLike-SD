package com.hse.sd.roguelike.model

import com.hse.sd.roguelike.model.actions.*
import com.hse.sd.roguelike.model.effects.EffectType

// class that stores the model
class ModelHandler {
    private val logic = ModelLogic()
    private var actions: MutableList<Action>
    private var canMove = true
    private var canAttack = true

    init {
        actions = logic.newGame().toMutableList()
    }

    // handles move action
    fun onMove(direction: com.hse.sd.roguelike.model.Direction) {
        if (logic.canMainCharacterMove(direction) && canMove) {
            actions.addAll(logic.mainCharacterMove(direction))
            canMove = false
            canAttack = false
        }
    }

    fun onAttack() {
        if (canAttack) {
            actions.addAll(logic.mainCharacterAttack())
            canAttack = false
        }
    }

    // returns all actions from last tick
    fun onTick(): List<Action> {
        val actionsFromLastTick = actions

        actions = mutableListOf()
        canMove = true
        canAttack = true

        val queue = ArrayList<Action>()
        queue.addAll(actionsFromLastTick)
        queue.addAll(logic.tick())
//         TODO("Ensure one HeroMoved action on list")
        var i = 0
        while (i < queue.size) {
            val action = queue[i]
            queue.addAll(handleAction(action))
            i++
        }
        return queue.toList()
    }

    fun onEquip(field: Int) {
        val hero = logic.environment.mainCharacter
        actions.addAll(
            hero.equip(field)
        )
    }

    fun onThrow() {
        val hero = logic.environment.mainCharacter
        actions.addAll(
            hero.throwFromHand(logic.environment.map)
        )
    }

    fun onPick() {
        val hero = logic.environment.mainCharacter
        val tile = logic.environment.map.getTile(hero.position)

        if (tile.item != null) {
            actions.addAll(
                hero.addItem(tile.item!!)
            )
        }
    }

    private fun handleAction(action: Action): List<Action> {
        return when (action) { // TODO("Other actions")
            is MobMoved -> {
                val direction = com.hse.sd.roguelike.model.Direction(action.dx, action.dy)
                val pos = action.actor.position
                logic.environment.map.moveActor(pos - direction, pos)
            }
            is HeroAttacked -> {
                val hero = logic.environment.mainCharacter
                val pos = hero.position + action.direction
                val actor = logic.environment.map.getTile(pos).actor

                if (actor != null) {
                    val effect = logic.environment.effectFactory.getEffect(EffectType.CONFUSION)
                    effect.applyToActor(actor)
                }
                actor?.onAttack(hero.power) ?: emptyList()
            }
            is MobAttacked -> {
                val mob = action.actor
                val pos = mob.position + action.direction
                val actor = logic.environment.map.getTile(pos).actor

                actor?.onAttack(mob.power) ?: emptyList()
            }
            is MobKilled -> {
                logic.environment.mobs.remove(action.mob)
                val tile = logic.environment.map.getTile(action.mob.position)
                tile.actor = null

                val hero = logic.environment.mainCharacter

                hero.addExp(action.mob.keepExp)

                emptyList()
            }
            is ItemPickedByHero -> {
                val hero = logic.environment.mainCharacter
                val tile = logic.environment.map.getTile(hero.position)

                tile.item = null

                emptyList()
            }
            is MobCreated -> {
                logic.environment.mobs.add(action.actor)
                emptyList()
            }
            else -> emptyList()
        }
    }

}
