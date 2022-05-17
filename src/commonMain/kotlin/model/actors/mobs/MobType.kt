package model.actors.mobs

import model.Environment

enum class MobType {
    ZOMBIE {
        override fun build(config: MobConfig, environment: Environment): Mob {
            return Zombie(
                config.position,
                config.hp,
                config.power,
                config.keepExp,
                config.strategy.build(environment)
            )
        }
    },
    ZOMBIE_IN_MASK {
        override fun build(config: MobConfig, environment: Environment): Mob {
            return ZombieInMask(
                config.position,
                config.hp,
                config.power,
                config.keepExp,
                config.strategy.build(environment)
            )
        }
    },
    SKELETON {
        override fun build(config: MobConfig, environment: Environment): Mob {
            return Skeleton(
                config.position,
                config.hp,
                config.power,
                config.keepExp,
                config.strategy.build(environment)
            )
        }
    },
    DRAGON {
        override fun build(config: MobConfig, environment: Environment): Mob {
            return Dragon(
                config.position,
                config.hp,
                config.power,
                config.keepExp,
                config.strategy.build(environment)
            )
        }
    },
    BIG_ZOMBIE {
        override fun build(config: MobConfig, environment: Environment): Mob {
            return BigZombie(
                config.position,
                config.hp,
                config.power,
                config.keepExp,
                config.strategy.build(environment)
            )
        }
    },
    POISONOUS_MOLD {
        override fun build(config: MobConfig, environment: Environment): Mob {
            return PoisonousMold(
                config.position,
                config.hp,
                config.power,
                config.keepExp,
                config.strategy.build(environment)
            )
        }
    };

    abstract fun build(config: MobConfig, environment: Environment): Mob
}