package model

// position in two-dimensional space
data class Position(var x: Int, var y: Int) {

    operator fun plus(direction: Direction): Position {
        return Position(x + direction.deltaX, y + direction.deltaY)
    }

    operator fun minus(other: Position): Direction {
        return Direction(x - other.x, y - other.y)
    }

    operator fun minus(direction: Direction): Position {
        return Position(x - direction.deltaX, y - direction.deltaY)
    }
}

