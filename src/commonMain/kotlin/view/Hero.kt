package view

import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.sprite
import com.soywiz.korge.view.tween.moveTo
import com.soywiz.korio.async.delay

class Hero(container: Container) {
    private val heroSprite = container.sprite(TileAnimation.Hero.idle).also {
        it.playAnimationLooped(spriteDisplayTime = 100.milliseconds)
    }
    private val knifeSprite = container.sprite(TileAnimation.Weapons.Knife.sprite).also {
        it.playAnimationLooped(spriteDisplayTime = 1000.milliseconds)
    }
    private var x = 0
    private var y = 0

    private var isMoving = false

    fun setPosition(newX: Int, newY: Int) {
        x = newX
        y = newY
        heroSprite.position(16 * x, 16 * y)
        knifeSprite.position(16 * x, 16 * y)
    }

    suspend fun changePosition(dx: Int, dy: Int) {
        if (isMoving) {
            return
        }
        isMoving = true
        x += dx
        y += dy
        heroSprite.playAnimation(TileAnimation.Hero.run, spriteDisplayTime = 60.milliseconds)
        heroSprite.moveTo(16 * x, 16 * y, time = 60.milliseconds)
        delay(60.milliseconds)
        heroSprite.playAnimationLooped(TileAnimation.Hero.idle, spriteDisplayTime = 100.milliseconds)
        isMoving = false
    }
}