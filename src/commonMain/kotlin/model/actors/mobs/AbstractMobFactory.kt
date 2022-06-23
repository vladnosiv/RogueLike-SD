package model.actors.mobs

import model.Environment

interface AbstractMobFactory {
    fun createMob(type: MobType, config: MobConfig, environment: Environment): Mob
}
