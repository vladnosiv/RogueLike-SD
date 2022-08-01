package com.hse.sd.roguelike.model.map

import com.hse.sd.roguelike.model.Position
import com.hse.sd.roguelike.model.actors.MainCharacter
import kotlin.test.*

class MapTest {

    private fun generateFlat(height: Int, width: Int): Map {
        val config = MapGeneratorConfig(height, width)
        val gen = FloorMapGenerator(config)
        return gen.generateMap()
    }

    @Test
    fun testGenerate() {
        val map = generateFlat(5, 5)

        val field = map.field

        assertEquals(5, field.size)

        for(row in field) {
            assertEquals(5, row.size)
        }

    }

    @Test
    fun testPositions() {
        val map = generateFlat(5, 5)

        for(i in 0..4) {
            for(j in 0..4) {
                assertTrue(map.isPositionOnField(Position(i, j)))
            }
        }

        val outside = listOf(
            Position(-1, 0),
            Position(1, -1),
            Position(5, 5),
            Position(5, 4),
            Position(10000, 10000),
            Position(-100000, -100000)
        )
        for(point in outside) {
            assertFalse(map.isPositionOnField(point))
        }
    }

    @Test
    fun testMoves() {
        val map = generateFlat(5, 5)

        val pos = Position(2, 2)
        val hero = MainCharacter(pos, 100, 1, 100)
        map.createMainCharacter(pos, hero)

        assertEquals(map.getTile(pos).actor, hero)

        map.moveActor(pos, pos + com.hse.sd.roguelike.model.Direction.DOWN)

        assertNull(map.getTile(pos).actor)
        assertEquals(hero, map.getTile(pos + com.hse.sd.roguelike.model.Direction.DOWN).actor)
    }
}
