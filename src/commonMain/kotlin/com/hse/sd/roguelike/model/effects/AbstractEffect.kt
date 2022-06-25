package com.hse.sd.roguelike.model.effects

import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actors.Actor

//interface for effects
abstract class AbstractEffect(val environment: com.hse.sd.roguelike.model.Environment) {
    abstract fun onStart(): List<Action>
    abstract fun onFinish(): List<Action>

    abstract fun applyToActor(actor: Actor)
}
