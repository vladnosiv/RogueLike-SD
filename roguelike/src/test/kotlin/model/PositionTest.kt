package model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PositionTest {
    @Test
    fun testMoves() {
        val startPosition = Position(0, 0)

        assertEquals(Position(0, -1), startPosition + Move.UP)
        assertEquals(Position(0, 1), startPosition + Move.DOWN)
        assertEquals(Position(-1, 0), startPosition + Move.LEFT)
        assertEquals(Position(1, 0), startPosition + Move.RIGHT)
    }
}
