package model

import model.actions.*
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.collections.ArrayList

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
    fun onMove(direction: Direction) {
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

    }

    fun onThrow() {

    }

    private fun handleAction(action: Action): List<Action> {
        return when (action) { // TODO("Other actions")
            is MobMoved -> {
                val direction = Direction(action.dx, action.dy)
                val pos = action.actor.position
                logic.environment.map.moveActor(pos - direction, pos)
            }
            is HeroAttacked -> {
                val hero = logic.environment.mainCharacter
                val pos = hero.position + action.direction
                val actor = logic.environment.map.getTile(pos).actor

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
                logic.environment.map.getTile(action.mob.position).actor = null
                emptyList()
            }
            else -> emptyList()
        }
    }

}
