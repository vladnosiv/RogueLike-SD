package model.actors.mobs

import model.Position
import model.actors.mobs.strategies.Strategy

class PoisonousMold(position: Position, hp: Int, power: Int, keepExp: Int, strategy: Strategy) :
    Mob(position, hp, power, keepExp, strategy) {
    override val type: MobType
        get() = MobType.POISONOUS_MOLD
}
