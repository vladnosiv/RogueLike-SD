package model

// the class that stores the environment
class Environment(
    generator: MapGenerator,
    mainCharacterConfig: MainCharacterConfig
) {
    var map = generator.genMap()
    var mainCharacter = MainCharacter(mainCharacterConfig.position, mainCharacterConfig.hp, mainCharacterConfig.exp)
    val mobs = mutableListOf<Mob>()
    val timer = Timer()

    init {
        map.getTile(mainCharacterConfig.position).actor = mainCharacter
    }

    // returns true if the main character can move in the move direction, otherwise false
    fun canMainCharacterMove(move: Move): Boolean {
        val newPosition = mainCharacter.position + move
        return map.isPositionOnField(newPosition) && map.getTile(newPosition).type.isPassable()
    }

    // moves main character
    fun mainCharacterMove(move: Move) {
        assert(canMainCharacterMove(move))
        map.getTile(mainCharacter.position).actor = null
        mainCharacter.position += move
        map.getTile(mainCharacter.position).actor = mainCharacter
    }

    // going to the next tick
    fun tick() {
        timer.tick()
    }
}
