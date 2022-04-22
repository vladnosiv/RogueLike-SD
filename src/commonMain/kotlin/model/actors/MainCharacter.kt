package model.actors

import model.Direction
import model.Position
import model.actions.Action
import model.actions.HeroAttacked
import model.actions.HeroChangedDirection
import model.actions.HeroMoved

// class for main character
class MainCharacter(position: Position, hp: Int, var exp: Int) : Actor(position, hp) {
    var direction = Direction.RIGHT

    fun makeMove(direction: Direction): List<Action> {
        return if (this.direction != direction) {
            this.direction = direction
            position += direction
            listOf(
                HeroChangedDirection(direction),
                HeroMoved(direction.deltaX, direction.deltaY)
            )
        } else {
            position += direction
            listOf(HeroMoved(direction.deltaX, direction.deltaY))
        }
    }

    fun attack(): List<Action> {
        return listOf(
            HeroAttacked(direction)
        )
    }

    fun attack(direction: Direction): List<Action> {
        return if (this.direction != direction) {
            this.direction = direction
            listOf(
                HeroChangedDirection(direction),
                HeroAttacked(direction)
            )
        } else {
            listOf(HeroAttacked(direction))
        }
    }
}
