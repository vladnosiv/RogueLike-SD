package model.actors.mobs

import model.Environment

interface AbstractMobFactory {
    fun createMob(config: MobConfig, environment: Environment): Mob
}
