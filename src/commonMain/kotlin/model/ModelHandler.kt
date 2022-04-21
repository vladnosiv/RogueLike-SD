package model

import model.actions.Action
import model.actions.HeroMoved
import model.actions.HeroPlaced
import model.actions.MapChanged

// class that stores the model
class ModelHandler {
    private val logic = ModelLogic()
    private var actions = mutableListOf<Action>()
    private var canMove = true

    init {
        actions = mutableListOf(
            MapChanged(FloorMapGenerator(MapGeneratorConfig(32, 32)).genMap().field),
            HeroPlaced(Position(16, 16))
        )
    }

    // handles move action
    fun onMove(move: Move) {
        if (logic.canMainCharacterMove(move) && canMove) {
            actions.addAll(logic.mainCharacterMove(move))
            canMove = false
        }
    }

    fun onAttack() {}

    // returns all actions from last tick
    fun onTick(): List<Action> {
        val actionsFromLastTick = actions

        actions = mutableListOf()
        canMove = true

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
