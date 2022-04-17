package controller


class Game(ui: view.UI, val logic: model.ModelHandler, val commands: KeyboardHandler) {

    private val mapUIRepr = ui.createMapRepr()
    private val heroUIRepr = ui.createActorRepr()

    fun tick() {
        for (cmd: Command in commands.getAll()) {
            when (cmd) {
                Command.MOVE_UP -> logic.onMove(model.Move.UP)
                Command.MOVE_DOWN -> logic.onMove(model.Move.DOWN)
                Command.MOVE_LEFT -> logic.onMove(model.Move.LEFT)
                Command.MOVE_RIGHT -> logic.onMove(model.Move.RIGHT)
            }
        }

        for (action: model.Action in logic.onTick()) {
            if (action is model.ActorMoved) {
                heroUIRepr.place(action.position.x, action.position.y)
            } else if (action is model.MapChanged) {
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

        commands.clear()
    }
}