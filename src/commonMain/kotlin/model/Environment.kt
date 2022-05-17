package model

import model.actors.MainCharacter
import model.actors.Mob
import model.actors.MobConfig
import model.effects.EffectFactory
import model.map.Map

//keeps all info about game world
class Environment(var map: Map, var mainCharacter: MainCharacter, mobConfigs: List<MobConfig>) {
    val timer = Timer()
    val effectFactory: EffectFactory = EffectFactory(this)
    val mobs = mutableListOf<Mob>()

    init {
        for(config in mobConfigs) {
            mobs.add(Mob(config.position, config.hp, config.hp, config.keepExp, config.strategy.build(this)))
        }
    }
}