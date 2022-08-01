package com.hse.sd.roguelike.model.actors.mobs.strategies

import com.hse.sd.roguelike.model.Environment
import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actors.mobs.Mob

//doesn't moves
class PassiveStrategy(override val environment: Environment) : Strategy {
    override val type: StrategyType
        get() = StrategyType.PASSIVE

    override fun strategyAct(mob: Mob): List<Action> {
        return emptyList()
    }
}