package model.map

// enum for different tile types
enum class TileType {
    FLOOR {
        override fun isPassable(): Boolean {
            return true
        }
    },
    WALL {
        override fun isPassable(): Boolean {
            return false
        }
    };

    abstract fun isPassable(): Boolean
}
