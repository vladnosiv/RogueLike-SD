package view


enum class Direction {
	UP {
		override fun side() = Direction.UP
	},

	UP_LEFT {
		override fun side() = Direction.LEFT
	},

	UP_RIGHT {
		override fun side() = Direction.RIGHT
	},

	DOWN {
		override fun side() = Direction.DOWN
	},

	DOWN_LEFT {
		override fun side() = Direction.LEFT
	},

	DOWN_RIGHT {
		override fun side() = Direction.RIGHT
	},

	LEFT {
		override fun side() = Direction.LEFT
	},

	RIGHT {
		override fun side() = Direction.RIGHT
	};

	abstract fun side(): Direction
}