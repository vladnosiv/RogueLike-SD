package view

import com.soywiz.korge.view.*
import com.soywiz.korge.view.tween.moveTo
import view.sprites.TileAnimation


class UI(
    container: Container
) {
    private val uiContainer = container.container()
    private val hpBar = mutableListOf<Sprite>()

    private var x = 0
    private var y = 0

    suspend fun setPosition(x: Int, y: Int) {
        this.x = x
        this.y = y
        for (i in 0..hpBar.size) {
            hpBar[i].moveTo(16 * (x + i), 16 * y)
        }
    }

    fun displayHP(hp: Int, maxHp: Int) {
        if (maxHp % 2 != 0) {
            throw IllegalArgumentException("The maximum hp must be an even number")
        }
        if (hp < 0 || hp > maxHp) {
            throw IllegalArgumentException("The current hp must be a non-negative number no greater than the maxHp")
        }
        hpBar.forEach { it.stopAnimation() }
        hpBar.clear()
        for (i in 0..hp / 2) {
            hpBar.add(uiContainer.sprite(TileAnimation.UI.FullHeart))
        }
        if (hp % 2 == 1) {
            hpBar.add(uiContainer.sprite(TileAnimation.UI.HalfHeart))
        }
        for (i in 0..maxHp / 2 - hpBar.size) {
            hpBar.add(uiContainer.sprite(TileAnimation.UI.EmptyHeart))
        }
        for (i in 0 until hpBar.size) {
            hpBar[i].position(16 * (x + i), 16 * y)
            hpBar[i].playAnimation()
        }
    }

}