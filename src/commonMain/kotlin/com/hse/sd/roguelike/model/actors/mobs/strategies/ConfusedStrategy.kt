package com.hse.sd.roguelike.model.actors.mobs.strategies

import com.hse.sd.roguelike.model.Direction
import com.hse.sd.roguelike.model.Environment
import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actions.MobMoved
import com.hse.sd.roguelike.model.actors.mobs.Mob
import kotlin.random.Random

//moves randomly
class ConfusedStrategy(override val environment: Environment, override val decorated: Strategy?) : StrategyDecorator {
    override val type: StrategyType
        get() = StrategyType.CONFUSED

    constructor(environment: Environment) : this(environment, null)

    override fun strategyAct(mob: Mob): List<Action> {
        val rand = Random.nextBoolean()

        if (rand && decorated != null) {
            return getDecoratedAction(mob)
        } else {
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
}
