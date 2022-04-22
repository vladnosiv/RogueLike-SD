package model.map

import model.actors.Actor

// class stores information about tile
// TODO: replace x, y with Position(x, y)
data class Tile(var type: TileType, var x: Int, var y: Int) {
    var actor: Actor? = null

    fun isEmpty(): Boolean {
        return actor == null
    }
}
