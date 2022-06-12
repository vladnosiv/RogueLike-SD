package model

import model.actions.*
import model.actors.MainCharacter
import model.items.Sword

// the class that stores the environment
class ModelLogic {
    lateinit var environment: Environment
    var lvl: Int = 0

    fun newGame(): List<Action> {
        val actions = mutableListOf<Action>()

        lvl = 1
        val environConfig = EnvironmentConfig(lvl)
        val mainCharacterConfig = environConfig.mainCharacterConfig
        val map = environConfig.generator.genMap()

        for ((pos, item) in environConfig.items) {
            actions.addAll(map.createItem(pos, item))
        }

        val mainCharacter = MainCharacter(
            mainCharacterConfig.position,
            mainCharacterConfig.hp,
            mainCharacterConfig.power,
            mainCharacterConfig.exp
        )

        actions.let {
            it.addAll(mainCharacter.addItem(Sword()))
            it.addAll(mainCharacter.equip(0))
        }

        actions.addAll(map.createMainCharacter(mainCharacterConfig.position, mainCharacter))
        actions.addAll(
            listOf(
                MapChanged(map.field),
                HeroChangedDirection(mainCharacterConfig.direction),
                HeroHPChanged(mainCharacterConfig.hp, mainCharacterConfig.hp)
            )
        )

        val mobConfigs = environConfig.mobConfigs

        environment = Environment(
            map,
            mainCharacter,
            mobConfigs
        )

        for (mob in environment.mobs) {
            actions.addAll(map.createMob(mob.position, mob))
        }

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
