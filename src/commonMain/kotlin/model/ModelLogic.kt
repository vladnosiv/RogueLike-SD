package model

import model.actions.*
import model.actors.MainCharacter

// the class that stores the environment
class ModelLogic {
    lateinit var environment: Environment
    var lvl: Int = 0

    fun newGame(): List<Action> {
        lvl = 1
        val environConfig = EnvironmentConfig(lvl)
        val mainCharacterConfig = environConfig.mainCharacterConfig
        val map = environConfig.generator.genMap()
        val mainCharacter = MainCharacter(
            mainCharacterConfig.position,
            mainCharacterConfig.hp,
            mainCharacterConfig.exp
        )
        environment = Environment(
            map,
            mainCharacter
        )

//        environment.map.getTile(mainCharacterConfig.position).actor = environment.mainCharacter

        val actions = environment.map.createMainCharacter(mainCharacterConfig.position, mainCharacter).toMutableList()
        actions.addAll(
            listOf(
                MapChanged(map.field),
                HeroChangedDirection(mainCharacterConfig.direction),
                HeroHPChanged(mainCharacterConfig.hp, mainCharacterConfig.hp)
            )
        )


        return actions
    }

    // returns true if the main character can move in the move direction, otherwise false
    fun canMainCharacterMove(direction: Direction): Boolean {
        val newPosition = environment.mainCharacter.position + direction
        return environment.map.isPositionOnField(newPosition) &&
                environment.map.getTile(newPosition).type.isPassable()
    }

    // moves main character
    fun mainCharacterMove(direction: Direction): List<Action> {
        assert(canMainCharacterMove(direction))
        val position = environment.mainCharacter.position
        val actions = environment.map.moveActor(position, position + direction).toMutableList()
        actions.addAll(environment.mainCharacter.makeMove(direction))
        return actions
    }

    fun mainCharacterAttack(): List<Action> {
        return environment.mainCharacter.attack()
    }

    // going to the next tick
    fun tick(): List<Action> {
        return environment.timer.tick()
//        return environment.mobs.map { it.makeMove() }.flatMap { it.asIterable() }
    }
}
