package model.actors.mobs.strategies

import model.Environment
import model.actions.Action
import model.actors.mobs.Mob

// interface with strategy for mob
interface Strategy {
    val type: StrategyType

    val environment: Environment

    fun makeAct(mob: Mob): List<Action>
}
