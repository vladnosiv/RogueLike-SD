package model

class Position(var x: Int, var y: Int) {
    constructor(position: Position, move: Move) : this(position.x + move.deltaX, position.y + move.deltaY)
}
