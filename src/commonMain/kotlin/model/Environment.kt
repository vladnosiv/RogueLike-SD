package model

class Environment(
    generator: MapGenerator,
    mainCharacterConfig: MainCharacterConfig
) {
    var map = generator.genMap()
    var mainCharacter = MainCharacter(mainCharacterConfig.position, mainCharacterConfig.hp, mainCharacterConfig.exp)
    val mobs = mutableListOf<Mob>()
    val timer = Timer()

    fun canMainCharacterMove(move: Move): Boolean {
        return map.isPositionOnField(mainCharacter.position + move)
    }

    fun mainCharacterMove(move: Move) {
        assert(canMainCharacterMove(move))
        mainCharacter.position += move
    }

    fun tick() {
        timer.tick()
    }
}
