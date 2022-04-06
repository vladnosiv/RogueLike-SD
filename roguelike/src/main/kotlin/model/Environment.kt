package model

class Environment {
    var map = Map(10, 10)
    var mainCharacter = MainCharacter(Position(0, 0), 100)
    val mobs = emptyList<Mob>()
    val timer = Timer()
}
