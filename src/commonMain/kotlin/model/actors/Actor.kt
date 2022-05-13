package model.actors

import model.Direction
import model.Position
import model.actions.Action
import model.effects.AbstractEffect
import model.effects.EffectType

// base class for storing information about actors
abstract class Actor(var position: Position, var hp: Int, var power: Int) {
    val effects = mutableSetOf<EffectType>()

    abstract fun onAttack(power: Int): List<Action>
    abstract fun onKill(): List<Action>
}
