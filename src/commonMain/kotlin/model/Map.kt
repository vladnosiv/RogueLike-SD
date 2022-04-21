package model

import model.actions.Action

// class that stores a map of tiles
data class Map(val field: List<List<Tile>>) {
    // checking that the transmitted position is on the field

    fun moveActor(from: Position, to: Position): List<Action> {
        assert(isPositionOnField(from))
        assert(isPositionOnField(to))

        val fromTile = getTile(from)
        val toTile = getTile(to)

        assert(fromTile.actor != null)
        assert(toTile.actor == null)

        toTile.actor = fromTile.actor
        fromTile.actor = null

        return emptyList()
    }

    fun isPositionOnField(position: Position): Boolean {
        val yOK = (0 <= position.y && position.y < field.size)
        val xOK = (0 <= position.x && position.x < field[position.y].size)
        return xOK && yOK
    }

    // returns tile by position
    fun getTile(position: Position): Tile {
        assert(isPositionOnField(position))
        return field[position.x][position.y]
    }
}
