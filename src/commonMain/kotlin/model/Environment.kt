package model

import model.actors.MainCharacter
import model.actors.Mob
import model.effects.EffectFactory
import model.map.Map

//keeps all info about game world
class Environment(var map: Map, var mainCharacter: MainCharacter) {
    lateinit var mobs: MutableList<Mob>
    val timer = Timer()
    val effectFactory: EffectFactory = EffectFactory(this)

    fun initMobs(mobs: List<Mob>) {
        this.mobs = mobs.toMutableList()
    }
}