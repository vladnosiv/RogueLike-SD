package model.actors

import model.Direction
import model.Position
import model.actions.*

// class that stores information about a mob
class Mob(position: Position, hp: Int, var strategy: Strategy) : Actor(position, hp) {
    var direction = Direction.RIGHT

    private fun applyMove(direction: Direction): List<Action> {
        return if (this.direction != direction) {
            this.direction = direction
            position += direction
            listOf(
                MobChangedDirection(direction),
                MobMoved(this, direction.deltaX, direction.deltaY, direction)
            )
        } else {
            position += direction
            listOf(MobMoved(this, direction.deltaX, direction.deltaY, direction))
        }
    }

    fun makeMove(): List<Action> {
        val actions = strategy.makeAct(this)
        assert(actions.size == 1)
        return applyMove((actions[0] as MobMoved).direction)
    }
}
