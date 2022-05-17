package model.actors.mobs

import model.Direction
import model.Position
import model.actions.*
import model.actors.Actor
import model.actors.mobs.strategies.Strategy

// class that stores information about a mob
abstract class Mob(position: Position, hp: Int, power: Int, val keepExp: Int, var strategy: Strategy) :
    Actor(position, hp, power) {
    abstract val type: MobType
    var direction = Direction.RIGHT

    private fun applyMove(direction: Direction): List<Action> {
        return if (this.direction != direction) {
            this.direction = direction
            position += direction
            listOf(
                MobChangedDirection(this, direction),
                MobMoved(this, direction.deltaX, direction.deltaY, direction)
            )
        } else {
            position += direction
            listOf(MobMoved(this, direction.deltaX, direction.deltaY, direction))
        }
    }

    protected fun makeTypeSpecificAction(): List<Action> = listOf()

    fun makeMove(): List<Action> {
        val actions = makeTypeSpecificAction().toMutableList()

        actions.addAll(strategy.makeAct(this))

        for (action in actions) {
            if (action is MobMoved) {
                applyMove(action.direction)
            }
        }
        return actions
    }

    override fun onAttack(power: Int): List<Action> {
        val actions = mutableListOf<Action>()
        actions.add(MobDamaged(this, power))
        hp -= power
        if (hp <= 0) {
            actions.addAll(onKill())
        }
        return actions
    }

    override fun onKill(): List<Action> {
        return listOf(
            MobKilled(this)
        )
    }
}
