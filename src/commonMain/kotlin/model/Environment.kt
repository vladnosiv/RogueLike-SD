package model

import model.actors.MainCharacter
import model.actors.mobs.Mob
import model.actors.mobs.MobConfig
import model.effects.EffectFactory
import model.map.Map

//keeps all info about game world
class Environment(var map: Map, var mainCharacter: MainCharacter, mobConfigs: List<MobConfig>) {
    val timer = Timer()
    val effectFactory: EffectFactory = EffectFactory(this)
    val mobs = mutableListOf<Mob>()

    init {
        for (config in mobConfigs) {
            mobs.add(config.type.build(config, this))
        }
    }
}