package com.hse.sd.roguelike.model

import com.hse.sd.roguelike.model.actors.MainCharacter
import com.hse.sd.roguelike.model.actors.mobs.Mob
import com.hse.sd.roguelike.model.effects.EffectFactory
import com.hse.sd.roguelike.model.map.Map


//keeps all info about game world
class Environment(var map: Map, var mainCharacter: MainCharacter) {
    val timer = Timer()
    val effectFactory: EffectFactory = EffectFactory(this)

    val mobs = mutableListOf<Mob>()
}