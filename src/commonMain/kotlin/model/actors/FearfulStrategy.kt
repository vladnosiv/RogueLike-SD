package model.actors

import model.Direction
import model.Environment
import model.actions.Action
import model.actions.MobAttacked
import model.actions.MobMoved

//mob trying to have at least 5 tiles to hero
class FearfulStrategy(override val environment: Environment) : Strategy {
    override val type: StrategyType
        get() = StrategyType.FEARFUL

    override fun makeAct(mob: Mob): List<Action> {
        val mobPos = mob.position
        val heroPos = environment.mainCharacter.position

        val dist = (mobPos - heroPos).abs()
        if (dist > 5) {
            return emptyList()
        }

        for (direction in Direction.values()) {
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
