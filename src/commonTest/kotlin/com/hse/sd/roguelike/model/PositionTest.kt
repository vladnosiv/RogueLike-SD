package com.hse.sd.roguelike.model

import kotlin.test.*

internal class PositionTest {
    @Test
    fun testMoves() {
        val startPosition = Position(0, 0)

        assertEquals(Position(0, -1), startPosition + com.hse.sd.roguelike.model.Direction.UP)
        assertEquals(Position(0, 1), startPosition + com.hse.sd.roguelike.model.Direction.DOWN)
        assertEquals(Position(-1, 0), startPosition + com.hse.sd.roguelike.model.Direction.LEFT)
        assertEquals(Position(1, 0), startPosition + com.hse.sd.roguelike.model.Direction.RIGHT)
    }
}
