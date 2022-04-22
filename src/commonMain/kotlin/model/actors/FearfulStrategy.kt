package model.actors

import model.Direction
import model.Environment
import model.actions.Action
import model.actions.MobAttacked
import model.actions.MobMoved

class FearfulStrategy(override val environment: Environment) : Strategy {
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
                mob.position += direction
                return listOf(
                    MobMoved(mob, direction.deltaX, direction.deltaY)
                )
            }
        }

        return emptyList()
    }
}
