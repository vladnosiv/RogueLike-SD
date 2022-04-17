package view.sprites

import com.soywiz.kmem.toInt
import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korma.geom.Angle
import com.soywiz.korma.geom.degrees

data class Weapon(
    val sprite: SpriteAnimation,
    val defaultAngle: Angle = (-45).degrees,
    val hitAngle: Angle = 90.degrees,
    val getWeaponPosition: (x: Int, y: Int, direction: Int) -> Pair<Int, Int> = {
            x: Int, y: Int, dir: Int -> 16 * x + 5 + (dir == -1).toInt() * 6 to 16 * y + 12
    }
)