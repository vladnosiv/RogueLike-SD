package view

import com.soywiz.korge.view.*
import com.soywiz.korma.geom.degrees
import view.sprites.TileAnimation


class StatusBar(
    container: Container
) {
    private var inventoryOffset = 2
    private var inventorySize = 5
    private var selectedPos = 0

    private val uiContainer = container.container()
    private val hpBar = mutableListOf<Sprite>()
    private val inventoryBar = MutableList(inventorySize) { uiContainer.sprite(TileAnimation.UI.RedButtonOff) }
    private val selectedCell = uiContainer.sprite(TileAnimation.UI.BlueButtonOff)
    private val inventoryItems = MutableList<Sprite?>(inventorySize) { null }

    private var x = 0
    private var y = 0

    init {
        displayInventory()
    }

    fun setInventorySize(inventorySize: Int) {
        if (inventorySize < inventoryItems.size) {
            throw IllegalArgumentException("New inventory size must be no lower than number of items")
        }
        this.inventorySize = inventorySize
        displayInventory()
    }

    fun setPosition(x: Int, y: Int) {
        this.x = x
        this.y = y
        for (i in 0..hpBar.size) {
            hpBar[i].position(16 * (x + i), 16 * y)
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
        displayInventory()
    }

    fun selectCell(index: Int) {
        selectedPos = index
        displayInventory()
    }

    fun addItem(index: Int, spriteAnimation: SpriteAnimation) {
        inventoryItems.set(index, uiContainer.sprite(spriteAnimation))
        displayInventory()
    }

    fun delItem(index: Int) {
        inventoryItems.set(index, null)
        displayInventory()
    }

    private fun displayInventory() {
        val startX = 16 * (x + hpBar.size + inventoryOffset)
        for (i in 0 until inventorySize) {
            inventoryBar[i].position(startX + 16 * i, 16 * y)
            inventoryBar[i].playAnimation()
        }
        selectedCell.position(startX + 16 * selectedPos, 16 * y)
        selectedCell.playAnimation()
        for (i in 0 until inventoryItems.size) {
            inventoryItems[i]?.anchor(0.5, 0.0)
            inventoryItems[i]?.position(startX + 16 * (i + 1), 16 * y)
            inventoryItems[i]?.rotation(45.degrees)
            inventoryItems[i]?.playAnimation()
        }
    }

}