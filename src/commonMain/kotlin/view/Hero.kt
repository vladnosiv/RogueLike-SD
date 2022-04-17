package view

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.sprite

/**
 * Хранит класс игрока
 * Позволяет двигать спрайт персонажа
 */
class Hero(container: Container) {
    private val heroSprite = container.sprite(TileAnimation.Hero.idle).also {
        it.playAnimationLooped(spriteDisplayTime = 100.milliseconds)
    }
    private var x = 16
    private var y = 16

    fun setPosition(newX: Int, newY: Int) {
        x = newX
        y = newY
        heroSprite.position(16 * x, 16 * y)
    }

    fun changePosition(dx: Int, dy: Int) {
        x += dx
        y += dy
        if (x > 30) x = 30
        if (x < 1) x = 1
        if (y > 29) y = 29
        if (y < 0) y = 0
        heroSprite.position(16 * x, 16 * y)
    }
}
