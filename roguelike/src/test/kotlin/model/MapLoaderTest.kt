package model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MapLoaderTest {
    @Test
    fun testLoad() {
        val loader = MapLoader("test_map")
        val generator = FloorMapGenerator(MapGeneratorConfig(10, 10))
        assertEquals(loader.genMap(), generator.genMap())
    }
}
