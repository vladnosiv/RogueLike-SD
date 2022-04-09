package model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TimerTest {
    @Test
    fun testTick() {
        val timer = Timer()

        assertEquals(0, timer.timer)
        timer.tick()
        assertEquals(1, timer.timer)
        timer.tick()
        assertEquals(2, timer.timer)
    }
}
