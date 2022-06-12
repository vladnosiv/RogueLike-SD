package model.actors.mobs.strategies

import model.Environment
import model.actions.Action
import model.actors.mobs.Cowardly
import model.actors.mobs.Mob

// interface with strategy for mob
interface Strategy {
    val type: StrategyType

    val environment: Environment

    fun makeAct(mob: Mob): List<Action> {
        return if (mob is Cowardly && mob.hp < mob.initialHp / 2.0) {
            FearfulStrategy(environment).strategyAct(mob)
        } else {
            strategyAct(mob)
        }
    }

    fun strategyAct(mob: Mob): List<Action>
}
