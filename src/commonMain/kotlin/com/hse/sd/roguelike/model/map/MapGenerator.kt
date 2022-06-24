package com.hse.sd.roguelike.model.map

// interface for classes that should generate a map
interface MapGenerator {
    fun generateMap(): Map
}
