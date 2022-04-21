package model

// position in two-dimensional space
data class Position(var x: Int, var y: Int) {
    constructor(position: Position, direction: Direction) : this(position.x + direction.deltaX, position.y + direction.deltaY)

    operator fun plus(direction: Direction): Position {
        return Position(this, direction)
    }
}
