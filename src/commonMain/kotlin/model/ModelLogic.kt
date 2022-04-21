package model

import model.actions.Action

// the class that stores the environment
class ModelLogic {
    lateinit var environment: Environment
    var lvl: Int = 0

    init {
        newGame()
    }

    fun newGame() {
        lvl = 1
        val environConfig = EnvironmentConfig(lvl)
        val mainCharacterConfig = environConfig.mainCharacterConfig
        environment = Environment(
            environConfig.generator.genMap(),
            MainCharacter(
                mainCharacterConfig.position,
                mainCharacterConfig.hp,
                mainCharacterConfig.exp
            )
        )

        environment.map.getTile(mainCharacterConfig.position).actor = environment.mainCharacter
    }

    // returns true if the main character can move in the move direction, otherwise false
    fun canMainCharacterMove(move: Move): Boolean {
        val newPosition = environment.mainCharacter.position + move
        return environment.map.isPositionOnField(newPosition) &&
                environment.map.getTile(newPosition).type.isPassable()
    }

    // moves main character
    fun mainCharacterMove(move: Move): List<Action> {
        assert(canMainCharacterMove(move))
        val position = environment.mainCharacter.position
        val actions = environment.map.moveActor(position, position + move).toMutableList()
        actions.addAll(environment.mainCharacter.makeMove(move))
        return actions
    }

    // going to the next tick
    fun tick(): List<Action> {
        return environment.timer.tick()
//        return environment.mobs.map { it.makeMove() }.flatMap { it.asIterable() }
    }
}
