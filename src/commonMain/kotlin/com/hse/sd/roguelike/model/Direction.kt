package com.hse.sd.roguelike.model

// enum for moves
data class Direction(val deltaX: Int = 0, val deltaY: Int = 0) {
    companion object {
        val UP = Direction(0, -1)
        val DOWN = Direction(0, 1)
        val LEFT = Direction(-1, 0)
        val RIGHT = Direction(1, 0)

        fun values(): List<Direction> = listOf(
            UP,
            DOWN,
            LEFT,
            RIGHT
        )
    }

    fun abs() = kotlin.math.abs(deltaX) + kotlin.math.abs(deltaY)

    operator fun plus(other: Direction) =
        Direction(deltaX + other.deltaX, deltaY + other.deltaY)

    operator fun minus(other: Direction) =
        Direction(deltaX - other.deltaX, deltaY - other.deltaY)
}
