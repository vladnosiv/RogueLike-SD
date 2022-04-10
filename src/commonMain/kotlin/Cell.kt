import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.xy
import tileSize

class Cell(
    x: Int,
    y: Int,
    var tileType: TileType,
    val sprite: Sprite = Sprite(tileType.animation).xy(x * tileSize, y * tileSize),
    var lit: Boolean = false,
    var wasLit: Boolean = false
) {
    fun rebuild() {
        sprite.playAnimationLooped(tileType.animation, spriteDisplayTime = 250.milliseconds)
    }

    fun isBlocked(): Boolean = tileType.blocks
}