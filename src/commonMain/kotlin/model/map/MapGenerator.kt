package model.map

import model.map.Map

// interface for classes that should generate a map
interface MapGenerator {
    fun genMap(): Map
}
