package model

import model.actions.Action
import model.actions.HeroMoved
import model.actions.MapChanged

// class that stores the model
class ModelHandler {
    private val logic = ModelLogic()

    private var actions = mutableListOf<Action>(MapChanged(FloorMapGenerator(MapGeneratorConfig(32, 32)).genMap().field))

    // handles move action
    fun onMove(move: Move) {
        if (logic.canMainCharacterMove(move)) {
            actions.addAll(logic.mainCharacterMove(move))
        }
    }

    fun onAttack() {}

    // returns all actions from last tick
    fun onTick(): List<Action> {
        val actionsFromLastTick = actions
        actions = mutableListOf()
        logic.tick()
        return actionsFromLastTick.reversed().distinctBy { action -> action.javaClass } // TODO("Ensure one ActorMoved action on list")
    }
}
