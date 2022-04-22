package controller

import kotlin.collections.HashMap
import kotlinx.coroutines.*
import model.actions.*


class Game(private val ui: view.UI,
           private val logic: model.ModelHandler,
           private val commands: KeyboardHandler) {

    private val mapUIRepr = ui.createMapRepr()
    private val heroUIRepr = ui.createHeroRepr()
    private val mobsReprs = HashMap<model.actors.Actor, view.UI.ActorEventHandler>()

    init {
        GlobalScope.launch{ tick() }
    }

    suspend fun tick() {
        handleCommands()
        displayActions()
    }

    suspend private fun handleCommands() {
        for (cmd: Command in commands.getAll()) {
            when (cmd) {
                Command.MOVE_UP    -> logic.onMove(model.Direction.UP)
                Command.MOVE_DOWN  -> logic.onMove(model.Direction.DOWN)
                Command.MOVE_LEFT  -> logic.onMove(model.Direction.LEFT)
                Command.MOVE_RIGHT -> logic.onMove(model.Direction.RIGHT)
                Command.ATTACK     -> logic.onAttack()
            }
        }
        commands.clear()
    }

    suspend private fun displayActions() {
        for (action: Action in logic.onTick()) {
            println(action::class.simpleName)
            when (action) {
                is HeroPlaced           -> heroUIRepr.place(action.position.x, action.position.y)
                is HeroHPChanged        -> ui.displayHp(action.current, action.max)
                is HeroMoved            -> heroUIRepr.move(action.dx, action.dy)
                is HeroChangedDirection -> heroUIRepr.turn(viewDirection(action.direction))
                is HeroAttacked         -> heroUIRepr.hit()
                is MobCreated           -> {
                    mobsReprs.put(action.actor, ui.createMobRepr())
                    mobsReprs.get(action.actor)?.place(action.position.x, action.position.y)
                }
                is MobAttacked          -> mobsReprs.get(action.actor)?.hit()
                is MobMoved             -> mobsReprs.get(action.actor)?.move(action.dx, action.dy)
                is MapChanged           -> {
                    mapUIRepr.fill(action.field.map { row ->
                        row.map { tile ->
                            when (tile.type) {
                                model.map.TileType.FLOOR -> view.Tile(view.TileType.FLOOR, tile.x, tile.y)
                                else -> view.Tile(view.TileType.WALL, tile.x, tile.y)
                            }
                        }
                    })
                }
            }
        }
    }

    private fun viewDirection(direction: model.Direction): view.Direction {
        return when (direction) {
            model.Direction.UP   -> view.Direction.UP
            model.Direction.DOWN -> view.Direction.DOWN
            model.Direction.LEFT -> view.Direction.LEFT
            else                 -> view.Direction.RIGHT
        }
    }
}
