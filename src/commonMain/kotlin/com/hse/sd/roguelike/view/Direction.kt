package com.hse.sd.roguelike.view


enum class Direction {
	UP {
		override fun side() = UP
	},

	UP_LEFT {
		override fun side() = LEFT
	},

	UP_RIGHT {
		override fun side() = RIGHT
	},

	DOWN {
		override fun side() = DOWN
	},

	DOWN_LEFT {
		override fun side() = LEFT
	},

	DOWN_RIGHT {
		override fun side() = RIGHT
	},

	LEFT {
		override fun side() = LEFT
	},

	RIGHT {
		override fun side() = RIGHT
	};

	abstract fun side(): Direction
}