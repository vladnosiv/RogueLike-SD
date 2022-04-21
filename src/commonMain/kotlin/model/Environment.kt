package model

import model.map.Map

class Environment (var map: Map, var mainCharacter: MainCharacter){
//    var map = generator.genMap()
//    var mainCharacter = MainCharacter(mainCharacterConfig.position, mainCharacterConfig.hp, mainCharacterConfig.exp)
    val mobs = mutableListOf<Mob>()
    val timer = Timer()

}