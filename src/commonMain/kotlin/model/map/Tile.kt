package model.map

import model.actors.Actor
import model.items.Item

// class stores information about tile
// TODO: replace x, y with Position(x, y)
data class Tile(var type: TileType, var x: Int, var y: Int) {
    var actor: Actor? = null
    var item: Item? = null

    fun isEmpty(): Boolean {
        return actor == null
    }
}
