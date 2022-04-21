package model

import kotlin.test.*

internal class PositionTest {
    @Test
    fun testMoves() {
        val startPosition = Position(0, 0)

        assertEquals(Position(0, -1), startPosition + Direction.UP)
        assertEquals(Position(0, 1), startPosition + Direction.DOWN)
        assertEquals(Position(-1, 0), startPosition + Direction.LEFT)
        assertEquals(Position(1, 0), startPosition + Direction.RIGHT)
    }
}
