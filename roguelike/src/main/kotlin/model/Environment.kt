package model

class Environment(generator: MapGenerator, config: MapGeneratorConfig) {
    var map = generator.genMap(config)
    var mainCharacter = MainCharacter(Position(0, 0), 100)
    val mobs = emptyList<Mob>()
    val timer = Timer()
}
