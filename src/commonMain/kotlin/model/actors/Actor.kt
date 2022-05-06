package model.actors

import model.Direction
import model.Position
import model.actions.Action

// base class for storing information about actors
abstract class Actor(var position: Position, var hp: Int, var power: Int) {
    abstract fun onAttack(power: Int): List<Action>
    abstract fun onKill(): List<Action>
}
