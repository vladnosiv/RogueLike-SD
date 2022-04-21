package model

import model.actions.Action
import model.actions.HeroChangedDirection
import model.actions.HeroMoved

// class for main character
class MainCharacter(position: Position, hp: Int, var exp: Int) : Actor(position, hp) {
    var direction = Direction.RIGHT

    fun makeMove(direction: Direction): List<Action> {
        return if (this.direction != direction) {
            this.direction = direction
            listOf(HeroChangedDirection(direction))
        } else {
            position += direction
            listOf(HeroMoved(direction.deltaX, direction.deltaY))
        }
    }
}
