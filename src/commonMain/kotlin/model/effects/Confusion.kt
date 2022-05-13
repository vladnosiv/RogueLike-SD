package model.effects

import model.Environment
import model.actions.Action
import model.actions.EffectFinished
import model.actions.EffectStarted
import model.actors.Actor
import model.actors.ConfusedStrategy
import model.actors.Mob
import model.actors.Strategy

class Confusion(environment: Environment) : AbstractEffect(environment) {
    lateinit var prevStrategy: Strategy
    lateinit var mob: Mob

    override fun onStart(): List<Action> {
        mob.strategy = ConfusedStrategy(environment)
        environment.timer.addTask (100) {
            onFinish()
        }
        return listOf(
            EffectStarted(this)
        )
    }

    override fun onFinish(): List<Action> {
        mob.strategy = prevStrategy
        mob.effects.remove(EffectType.CONFUSION)
        return listOf(
            EffectFinished(this)
        )
    }

    override fun applyToActor(actor: Actor) {
        if (actor is Mob && !actor.effects.contains(EffectType.CONFUSION)) {
            mob = actor
            mob.effects.add(EffectType.CONFUSION)

            prevStrategy = mob.strategy

            environment.timer.addTask (0) {
                onStart()
            }
        }
    }
}
