package model

import model.actors.MainCharacter
import model.actors.Mob
import model.effects.EffectFactory
import model.map.Map

class Environment (var map: Map, var mainCharacter: MainCharacter){
//    var map = generator.genMap()
//    var mainCharacter = MainCharacter(mainCharacterConfig.position, mainCharacterConfig.hp, mainCharacterConfig.exp)
    lateinit var mobs: MutableList<Mob>
    val timer = Timer()
    val effectFactory: EffectFactory = EffectFactory(this)

    fun initMobs(mobs: List<Mob>) {
        this.mobs = mobs.toMutableList()
    }
}