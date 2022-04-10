package view

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.sprite

class Hero(container: Container) {
    private val heroSprite = container.sprite(TileAnimation.Hero.idle).also {
        it.playAnimationLooped(spriteDisplayTime = 100.milliseconds)
    }
    private var x = 0
    private var y = 0

    fun setPosition(newX: Int, newY: Int) {
        x = newX
        y = newY
        heroSprite.position(16 * x, 16 * y)
    }

    fun changePosition(dx: Int, dy: Int) {
        x += dx
        y += dy
        heroSprite.position(16 * x, 16 * y)
    }
}