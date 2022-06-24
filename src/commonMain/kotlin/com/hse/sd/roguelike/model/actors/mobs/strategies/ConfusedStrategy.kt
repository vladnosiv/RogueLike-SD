package com.hse.sd.roguelike.model.actors.mobs.strategies

import com.hse.sd.roguelike.model.Environment
import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actions.MobMoved
import com.hse.sd.roguelike.model.actors.mobs.Mob

//moves randomly
class ConfusedStrategy(override val environment: Environment) : Strategy {
    override val type: StrategyType
        get() = StrategyType.CONFUSED

    override fun strategyAct(mob: Mob): List<Action> {
        val mobPos = mob.position

        for (direction in com.hse.sd.roguelike.model.Direction.values().shuffled()) {
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
