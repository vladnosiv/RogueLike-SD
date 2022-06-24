package com.hse.sd.roguelike.view.sprites

import com.soywiz.kmem.toInt
import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korma.geom.Angle
import com.soywiz.korma.geom.degrees
import com.hse.sd.roguelike.view.Direction


data class Weapon(
    var sprite: SpriteAnimation,
    val defaultAngle: Angle = (-30).degrees,
    val hitAngle: Angle = 135.degrees,
    val hitDistance: Int = 6,
    val getWeaponPosition: (Int, Int, Direction) -> Pair<Int, Int> = {
        x: Int, y: Int, direction: Direction -> when (direction) {
            Direction.UP_LEFT    -> 16 * x + 12 to 16 * y + 12
            Direction.UP_RIGHT   -> 16 * x + 4  to 16 * y + 12
            Direction.DOWN_LEFT  -> 16 * x + 12 to 16 * y + 4
            Direction.DOWN_RIGHT -> 16 * x + 4  to 16 * y + 4
            Direction.LEFT       -> 16 * x + 11 to 16 * y + 12
            Direction.RIGHT      -> 16 * x + 5  to 16 * y + 12
            else                 -> 16 * x + 8  to 16 * y + 12
        }
    },
    val getWeaponAngle: (Direction) -> Angle = {
        direction: Direction ->  when (direction) {
            Direction.UP_LEFT    -> (0).degrees
            Direction.UP_RIGHT   -> (0).degrees
            Direction.DOWN_LEFT  -> (180).degrees
            Direction.DOWN_RIGHT -> (180).degrees
            Direction.LEFT       -> (+30).degrees
            Direction.RIGHT      -> (-30).degrees
            else                 -> (0).degrees
        }
    }
)
