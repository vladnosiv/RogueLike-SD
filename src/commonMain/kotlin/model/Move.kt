package model

enum class Move {
    UP {
        override val deltaY = -1
    },
    DOWN {
        override val deltaY = 1
    },
    LEFT {
        override val deltaX = -1
    },
    RIGHT {
        override val deltaX = 1
    };

    open val deltaX = 0
    open val deltaY = 0
}
