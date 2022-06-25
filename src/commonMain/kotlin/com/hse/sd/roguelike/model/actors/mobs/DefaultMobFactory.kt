package com.hse.sd.roguelike.model.actors.mobs

import com.hse.sd.roguelike.model.Environment

class DefaultMobFactory : AbstractMobFactory {
    override fun createMob(config: MobConfig, environment: Environment): Mob {
        when (config.type) {
            MobType.ZOMBIE -> return Zombie(
                config.position,
                config.hp,
                config.power,
                config.keepExp,
                config.strategy.build(environment)
            )
            MobType.ZOMBIE_IN_MASK -> return ZombieInMask(
                config.position,
                config.hp,
                config.power,
                config.keepExp,
                config.strategy.build(environment)
            )
            MobType.SKELETON -> return Skeleton(
                config.position,
                config.hp,
                config.power,
                config.keepExp,
                config.strategy.build(environment)
            )
            MobType.DRAGON -> return Dragon(
                config.position,
                config.hp,
                config.power,
                config.keepExp,
                config.strategy.build(environment)
            )
            MobType.BIG_ZOMBIE -> return BigZombie(
                config.position,
                config.hp,
                config.power,
                config.keepExp,
                config.strategy.build(environment)
            )
            MobType.POISONOUS_MOLD -> return PoisonousMold(
                config.position,
                config.hp,
                config.power,
                config.keepExp,
                config.strategy.build(environment),
                0.1
            )
        }

    }
}