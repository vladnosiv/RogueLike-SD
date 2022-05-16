package model.effects

import model.Environment
import model.actions.Action
import model.actors.Actor

//interface for effects
abstract class AbstractEffect(val environment: Environment) {
    abstract fun onStart(): List<Action>
    abstract fun onFinish(): List<Action>

    abstract fun applyToActor(actor: Actor)
}
