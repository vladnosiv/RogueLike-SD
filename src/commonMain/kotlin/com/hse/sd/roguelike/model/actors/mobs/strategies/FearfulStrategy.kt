package com.hse.sd.roguelike.model.actors.mobs.strategies

import com.hse.sd.roguelike.model.Environment
import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actions.MobMoved
import com.hse.sd.roguelike.model.actors.mobs.Mob


//mob trying to have at least 5 tiles to hero
class FearfulStrategy(override val environment: Environment) : Strategy {
    override val type: StrategyType
        get() = StrategyType.FEARFUL

    override fun strategyAct(mob: Mob): List<Action> {
        val mobPos = mob.position
        val heroPos = environment.mainCharacter.position

        val dist = (mobPos - heroPos).abs()
        if (dist > 5) {
            return emptyList()
        }

        for (direction in com.hse.sd.roguelike.model.Direction.values()) {
            val pos = mobPos + direction
            if (environment.map.canStep(pos) && (pos - heroPos).abs() > dist) {
                return listOf(
                    MobMoved(mob, direction.deltaX, direction.deltaY, direction)
                )
            }
        }

        return emptyList()
    }
}
