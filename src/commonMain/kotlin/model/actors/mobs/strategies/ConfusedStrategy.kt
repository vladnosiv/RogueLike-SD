package model.actors.mobs.strategies

import model.Direction
import model.Environment
import model.actions.Action
import model.actions.MobMoved
import model.actors.mobs.Mob

//moves randomly
class ConfusedStrategy(override val environment: Environment) : Strategy {
    override val type: StrategyType
        get() = StrategyType.CONFUSED

    override fun makeAct(mob: Mob): List<Action> {
        val mobPos = mob.position

        for (direction in Direction.values().shuffled()) {
            val pos = mobPos + direction
            if (environment.map.canStep(pos)) {
                return listOf(
                    MobMoved(mob, direction.deltaX, direction.deltaY, direction)
                )
            }
        }

        return emptyList()
    }
}
