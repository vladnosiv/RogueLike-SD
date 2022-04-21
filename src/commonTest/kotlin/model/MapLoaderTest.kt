package model

import model.map.FloorMapGenerator
import model.map.MapGeneratorConfig
import model.map.MapLoader
import kotlin.test.*

class MapLoaderTest {
    @Test
    fun testLoad() {
        val loader = MapLoader("test_map")
        val generator = FloorMapGenerator(MapGeneratorConfig(10, 10))
        assertEquals(loader.genMap(), generator.genMap())
    }
}
