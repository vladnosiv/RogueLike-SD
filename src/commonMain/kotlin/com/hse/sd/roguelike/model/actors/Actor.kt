package com.hse.sd.roguelike.model.actors

import com.hse.sd.roguelike.model.Position
import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.effects.EffectType

// base class for storing information about actors
abstract class Actor(var position: Position, var hp: Int, var power: Int) {
    val effects = mutableSetOf<EffectType>()

    abstract fun onAttack(power: Int): List<Action>
    abstract fun onKill(): List<Action>
}
