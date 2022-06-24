package com.hse.sd.roguelike.model

import java.nio.file.Path
import kotlin.io.path.Path

// a class that stores matching between the level name and the path in which it is stored
class LevelHolder {
    companion object {
        private val matching = mapOf(
            "test_map" to Path("levels/test_map.txt")
        )

        // returns path by level name
        fun getPathByName(name: String): Path {
            return matching[name]!!
        }
    }
}
