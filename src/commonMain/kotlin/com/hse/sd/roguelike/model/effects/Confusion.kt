package com.hse.sd.roguelike.model.effects

import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actions.EffectFinished
import com.hse.sd.roguelike.model.actions.EffectStarted
import com.hse.sd.roguelike.model.actors.Actor
import com.hse.sd.roguelike.model.actors.mobs.Mob
import com.hse.sd.roguelike.model.actors.mobs.strategies.ConfusedStrategy
import com.hse.sd.roguelike.model.actors.mobs.strategies.Strategy

//mob starts moving randomly
class Confusion(environment: com.hse.sd.roguelike.model.Environment) : AbstractEffect(environment) {
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
