package model

// class that stores the model
class ModelHandler {
    private val environment =
        Environment(FloorMapGenerator(MapGeneratorConfig(32, 32)), MainCharacterConfig(Position(16, 16), 100, 0))
    private var actions = mutableListOf<Action>(MapChanged(FloorMapGenerator(MapGeneratorConfig(32, 32)).genMap().field))

    // handles move action
    fun onMove(move: Move) {
        if (environment.canMainCharacterMove(move)) {
            val actor = environment.mainCharacter
            actions.add(ActorMoved(actor, Position(actor.position, move)))
            environment.mainCharacterMove(move)
        }
    }

    // returns all actions from last tick
    fun onTick(): List<Action> {
        val actionsFromLastTick = actions
        actions = mutableListOf()
        environment.tick()
        return actionsFromLastTick.reversed().distinctBy { action -> action.javaClass } // TODO("Ensure one ActorMoved action on list")
    }
}
