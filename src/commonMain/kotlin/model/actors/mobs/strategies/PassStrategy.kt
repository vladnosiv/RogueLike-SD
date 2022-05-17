package model.actors.mobs.strategies

import model.Environment
import model.actions.Action
import model.actors.mobs.Mob

//doesn't moves
class PassStrategy(override val environment: Environment) : Strategy {
    override val type: StrategyType
        get() = StrategyType.PASSIVE

    override fun makeAct(mob: Mob): List<Action> {
        return emptyList()
    }
}