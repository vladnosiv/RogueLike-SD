package controller


class Game(private val ui: view.UI,
           private val logic: model.ModelHandler,
           private val commands: KeyboardHandler) {

    private val mapUIRepr = ui.createMapRepr()
    private val heroUIRepr = ui.createActorRepr()

    init {
        displayActions()
    }

    fun tick() {
        handleCommands()
        displayActions()
    }

    private fun handleCommands() {
        for (cmd: Command in commands.getAll()) {
            when (cmd) {
                Command.MOVE_UP -> logic.onMove(model.Move.UP)
                Command.MOVE_DOWN -> logic.onMove(model.Move.DOWN)
                Command.MOVE_LEFT -> logic.onMove(model.Move.LEFT)
                Command.MOVE_RIGHT -> logic.onMove(model.Move.RIGHT)
                Command.ATTACK -> logic.onAttack()
            }
        }
        commands.clear()
    }

    private fun displayActions() {
        for (action: model.Action in logic.onTick()) {
            when (action) {
                is model.ActorMoved -> heroUIRepr.place(action.position.x, action.position.y)
                is model.MapChanged -> {
                    mapUIRepr.fill(action.field.map { row ->
                        row.map { tile ->
                            when (tile.type) {
                                model.TileType.FLOOR -> view.Tile(view.TileType.FLOOR, tile.x, tile.y)
                                else -> view.Tile(view.TileType.WALL, tile.x, tile.y)
                            }
                        }
                    })
                }
            }
        }
    }

}
