package view

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.sprite

class Hero(container: Container) {
    private val heroSprite = container.sprite(TileAnimation.Hero.idle).also {
        it.playAnimationLooped(spriteDisplayTime = 100.milliseconds)
    }

    fun changePosition(x: Int, y: Int) {
        heroSprite.position(16 * x, 16 * y)
    }
}