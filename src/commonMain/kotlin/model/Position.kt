package model

// position in two-dimensional space
data class Position(var x: Int, var y: Int) {
    constructor(position: Position, move: Move) : this(position.x + move.deltaX, position.y + move.deltaY)

    operator fun plus(move: Move): Position {
        return Position(this, move)
    }
}
