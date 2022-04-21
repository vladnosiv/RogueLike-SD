package controller

import kotlinx.coroutines.*


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
                Command.MOVE_UP -> logic.onMove(model.Direction.UP)
                Command.MOVE_DOWN -> logic.onMove(model.Direction.DOWN)
                Command.MOVE_LEFT -> logic.onMove(model.Direction.LEFT)
                Command.MOVE_RIGHT -> logic.onMove(model.Direction.RIGHT)
                Command.ATTACK -> logic.onAttack()
            }
        }
        commands.clear()
    }

    private fun displayActions() {
        for (action: model.actions.Action in logic.onTick()) {
            when (action) {
                is model.actions.HeroPlaced -> heroUIRepr.place(action.position.x, action.position.y)
                is model.actions.HeroHPChanged -> ui.displayHp(action.current, action.max)
                is model.actions.HeroMoved -> GlobalScope.launch{ heroUIRepr.move(action.dx, action.dy) }
                is model.actions.HeroAttacked -> GlobalScope.launch{ heroUIRepr.hit() }
                is model.actions.MapChanged -> {
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
