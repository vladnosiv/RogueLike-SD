package model

import model.actions.*
import model.map.FloorMapGenerator
import model.map.MapGeneratorConfig

// class that stores the model
class ModelHandler {
    private val logic = ModelLogic()
    private var actions: MutableList<Action>
    private var canMove = true
    private var canAttack = true

    init {
        actions = logic.newGame().toMutableList()
    }

    // handles move action
    fun onMove(direction: Direction) {
        if (logic.canMainCharacterMove(direction) && canMove) {
            actions.addAll(logic.mainCharacterMove(direction))
            canMove = false
        }
    }

    fun onAttack() {
        if (canAttack) {
            actions.addAll(logic.mainCharacterAttack())
            canAttack = false
        }
    }

    // returns all actions from last tick
    fun onTick(): List<Action> {
        val actionsFromLastTick = actions

        actions = mutableListOf()
        canMove = true
        canAttack = true

        actionsFromLastTick.addAll(logic.tick())
        // TODO("Ensure one HeroMoved action on list")
        for (action in actions) {
            actions.addAll(handleAction(action))
        }
        return actionsFromLastTick
    }

    private fun handleAction(action: Action): List<Action> {
        return when (action) { // TODO("Other actions")
            is HeroMoved -> emptyList()
            else -> throw Exception()
        }
    }

}
