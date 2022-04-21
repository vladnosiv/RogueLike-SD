package model.actors

import model.Position
import model.actions.Action

// class that stores information about a mob
class Mob(position: Position, hp: Int) : Actor(position, hp) {
    fun makeMove(): List<Action> {
        return emptyList() //TODO("Not yet implemented")
    }

    var strategy: Strategy? = null
}
