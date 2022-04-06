package model

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
