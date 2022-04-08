package model

class Map(private val field: List<List<Tile>>) {
    fun isPositionOnField(position: Position): Boolean {
        val yOK = (0 <= position.y && position.y < field.size)
        val xOK = (0 <= position.x && position.x < field[position.y].size)
        return xOK && yOK
    }
}
