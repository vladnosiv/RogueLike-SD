package com.hse.sd.roguelike.model.actors.mobs

import com.hse.sd.roguelike.model.Environment

interface AbstractMobFactory {
    fun createMob(config: MobConfig, environment: Environment): Mob
}
