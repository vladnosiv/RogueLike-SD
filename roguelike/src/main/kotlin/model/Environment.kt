package model

class Environment(generator: MapGenerator, config: MapGeneratorConfig) {
    var map = generator.genMap(config)
    var mainCharacter = MainCharacter(Position(0, 0), 100)
    val mobs = mutableListOf<Mob>()
    val timer = Timer()

    fun canMainCharacterMove(move: Move): Boolean {
        return map.isPositionOnField(Position(mainCharacter.position, move))
    }

    fun mainCharacterMove(move: Move) {
        assert(canMainCharacterMove(move))
        mainCharacter.position = Position(mainCharacter.position, move)
    }

    fun tick() {
        timer.tick()
    }
}
