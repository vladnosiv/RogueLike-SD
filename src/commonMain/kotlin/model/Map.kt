package model

// class that stores a map of tiles
data class Map(val field: List<List<Tile>>) {
    // checking that the transmitted position is on the field
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
