package model.actors

import model.Direction
import model.Position
import model.actions.Action

// class that stores information about a mob
class Mob(position: Position, hp: Int, var strategy: Strategy) : Actor(position, hp) {
    fun makeMove(): List<Action> {
        return strategy.makeAct(this)
    }
}
