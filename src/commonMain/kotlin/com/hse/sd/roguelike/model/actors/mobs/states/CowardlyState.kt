package com.hse.sd.roguelike.model.actors.mobs.states

import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actors.mobs.Mob
import com.hse.sd.roguelike.model.actors.mobs.strategies.FearfulStrategy
import com.hse.sd.roguelike.model.actors.mobs.strategies.Strategy

class CowardlyState(private val mob: Mob): State(mob) {
    override val stateType = StateType.COWARDLY
    private val fearfulStrategy: Strategy

    init {
        fearfulStrategy = FearfulStrategy(mob.strategy.environment)
    }

    override fun makeMove(): List<Action> {
        return fearfulStrategy.makeAct(mob)
    }

    override fun changeStateIfNeeded() {
        if (mob.hp >= mob.initialHp / 2) {
            mob.state = NormalState(mob)
        }
    }
}