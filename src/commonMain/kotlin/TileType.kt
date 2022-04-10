import com.soywiz.korge.view.SpriteAnimation

enum class TileType(val blocks: Boolean, val tunnelCost: Double) {
    WALL(true, 8.0),
    FLOOR(false, 1.0);

    lateinit var animation: SpriteAnimation
}