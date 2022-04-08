package model


class ModelHandler {
    private val environment = Environment(FloorMapGenerator(), MapGeneratorConfig(10, 10))
    private var actions = mutableListOf<Action>()

    fun onMove(move: Move) {
        if (environment.canMainCharacterMove(move)) {
            val actor = environment.mainCharacter
            actions.add(ActorMoved(actor, Position(actor.position, move)))
            environment.mainCharacterMove(move)
        }
    }

    fun onTick(): List<Action> {
        val actionsFromLastTick = actions
        actions = mutableListOf()
        environment.tick()
        return actionsFromLastTick // TODO("Ensure one ActorMoved action on list")
    }
}