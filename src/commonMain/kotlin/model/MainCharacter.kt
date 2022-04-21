package model

import model.actions.Action
import model.actions.HeroMoved

// class for main character
class MainCharacter(position: Position, hp: Int, var exp: Int) : Actor(position, hp) {
    fun makeMove(direction: Direction): List<Action> {
        position += direction
        return listOf(HeroMoved(direction.deltaX, direction.deltaY))
    }
}
