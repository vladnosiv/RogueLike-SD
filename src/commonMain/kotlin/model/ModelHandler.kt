package model

import model.actions.Action
import model.actions.ActorMoved
import model.actions.MapChanged

// class that stores the model
class ModelHandler {
    private val logic = ModelLogic()

    private var actions = mutableListOf<Action>(MapChanged(FloorMapGenerator(MapGeneratorConfig(32, 32)).genMap().field))

    // handles move action
    fun onMove(move: Move) {
        if (logic.canMainCharacterMove(move)) {
            val actor = logic.environment.mainCharacter
            actions.add(ActorMoved(actor, Position(actor.position, move)))
            logic.mainCharacterMove(move)
        }
    }

    // returns all actions from last tick
    fun onTick(): List<Action> {
        val actionsFromLastTick = actions
        actions = mutableListOf()
        logic.tick()
        return actionsFromLastTick.reversed().distinctBy { action -> action.javaClass } // TODO("Ensure one ActorMoved action on list")
    }

//    fun regenMap(gen: MapGenerator) {
//        logic.regen(gen)
//    }
}
