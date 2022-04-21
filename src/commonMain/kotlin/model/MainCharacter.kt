package model

import model.actions.Action
import model.actions.HeroMoved

// class for main character
class MainCharacter(position: Position, hp: Int, var exp: Int) : Actor(position, hp) {
    fun makeMove(move: Move): List<Action> {
        position += move
        return listOf(HeroMoved(move.deltaX, move.deltaY))
    }
}
