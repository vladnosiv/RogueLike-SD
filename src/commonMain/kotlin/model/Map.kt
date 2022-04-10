package model

data class Map(val field: List<List<Tile>>) {
    fun isPositionOnField(position: Position): Boolean {
        val yOK = (0 <= position.y && position.y < field.size)
        val xOK = (0 <= position.x && position.x < field[position.y].size)
        return xOK && yOK
    }

    fun getTile(position: Position): Tile {
        assert(isPositionOnField(position))
        return field[position.x][position.y]
    }
}
