package com.hse.sd.roguelike.model

import com.hse.sd.roguelike.model.map.FloorMapGenerator
import com.hse.sd.roguelike.model.map.MapGeneratorConfig
import com.hse.sd.roguelike.model.map.MapLoader
import kotlin.test.*

class MapLoaderTest {
    @Test
    fun testLoad() {
        val loader = MapLoader("test_map")
        val generator = FloorMapGenerator(MapGeneratorConfig(10, 10))
        assertEquals(loader.generateMap(), generator.generateMap())
    }
}
