package model

import model.actors.MainCharacter
import model.actors.mobs.Mob
import model.actors.mobs.MobConfig
import model.effects.EffectFactory
import model.map.Map

//keeps all info about game world
class Environment(var map: Map, var mainCharacter: MainCharacter) {
    val timer = Timer()
    val effectFactory: EffectFactory = EffectFactory(this)

    val mobs = mutableListOf<Mob>()
}