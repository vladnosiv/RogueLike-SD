package model

import java.nio.file.Path
import kotlin.io.path.Path

class LevelHolder {
    companion object {
        private val matching = mapOf(
            "test_map" to Path("levels/test_map.txt")
        )

        fun getPathByName(name: String): Path {
            return matching[name]!!
        }
    }
}
