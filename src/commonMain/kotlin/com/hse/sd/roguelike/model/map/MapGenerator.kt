package com.hse.sd.roguelike.model.map

import com.hse.sd.roguelike.model.map.Map

// interface for classes that should generate a map
interface MapGenerator {
    fun genMap(): Map
}
