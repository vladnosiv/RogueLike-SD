package model

import model.actions.*
import model.actors.mobs.DefaultMobFactory

// the class that stores the environment
class ModelLogic {
    lateinit var environment: Environment
    var lvl: Int = 0

    fun newGame(): List<Action> {
        val actions = mutableListOf<Action>()

        lvl = 1
        val environConfig = EnvironmentConfig(lvl)

        val builder = EnvironmentBuilder(DefaultMobFactory())
            .registerGenerator(environConfig.generator)
            .addMainCharacter(environConfig.mainCharacterConfig)
            .addMobs(*environConfig.mobConfigs.toTypedArray())
            .addItems(*environConfig.items.toTypedArray())

        environment = builder.generate()

        actions.addAll(builder.getActions())

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

        val tile = environment.map.getTile(position + direction)

        if (tile.isEmpty()) {
            val actions = environment.map.moveActor(position, position + direction).toMutableList()
            actions.addAll(environment.mainCharacter.makeMove(direction))
            return actions
        } else {
//            val actions = environment.map.moveActor(position, position + direction).toMutableList()
            val actions = mutableListOf<Action>()
            actions.addAll(environment.mainCharacter.attack(direction))

            return actions
        }
    }

    fun mainCharacterAttack(): List<Action> {
        return environment.mainCharacter.attack()
    }

    // going to the next tick
    fun tick(): List<Action> {
        val actions = environment.timer.tick().toMutableList()
        actions.addAll(environment.mobs.map { it.makeMove() }.flatMap { it.asIterable() })
        return actions
    }
}
