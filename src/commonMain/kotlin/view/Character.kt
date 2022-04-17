package view

import com.soywiz.klock.milliseconds
import com.soywiz.korge.animate.*
import com.soywiz.korge.view.*
import com.soywiz.korge.view.tween.rotateTo
import view.sprites.CharacterSprite
import view.sprites.Weapon


class Character(
    private val container: Container,
    private val character: CharacterSprite,
    private var weapon: Weapon? = null
) {
    private val characterSprite = container.sprite(character.idle).also {
        it.playAnimationLooped(spriteDisplayTime = character.idleDuration)
    }

    private val weaponSprite = weapon?.let { container.sprite(it.sprite).rotation(it.defaultAngle) }

    private var x = 0
    private var y = 0

    @Volatile private var isMoving = false
    @Volatile private var isHitting = false

    fun setWeapon(newWeapon: Weapon) {
        if (!isHitting) {
            weapon = newWeapon
        }
    }

    fun setPosition(newX: Int, newY: Int) {
        x = newX
        y = newY
        characterSprite.position(
            character.getPosition(x, y).first,
            character.getPosition(x, y).second
        )
        weaponSprite?.position(
            weapon!!.getWeaponPosition(x, y).first,
            weapon!!.getWeaponPosition(x, y).second
        )
        characterSprite.anchor(0.5, 1.0)
        weaponSprite?.anchor(0.5, 1.0)
    }

    suspend fun changePosition(dx: Int, dy: Int) {
        if (isMoving) {
            return
        }
        isMoving = true
        x += dx
        y += dy
        characterSprite.playAnimationLooped(character.run, spriteDisplayTime = character.runDuration)
        container.animateParallel {
            parallel {
                weaponSprite?.moveTo(
                    weapon!!.getWeaponPosition(x, y).first,
                    weapon!!.getWeaponPosition(x, y).second,
                    time = character.runDuration
                )
            }
            parallel {
                characterSprite.moveTo(
                    character.getPosition(x, y).first,
                    character.getPosition(x, y).second,
                    time = character.runDuration
                )
            }
        }
        characterSprite.playAnimationLooped(character.idle, spriteDisplayTime = character.idleDuration)
        isMoving = false
    }

    suspend fun hit() {
        if (isHitting) {
            return
        }
        isHitting = true
        weapon?.let { weaponSprite!!.rotateTo(angle = it.hitAngle, time = 150.milliseconds) }
        weapon?.let { weaponSprite!!.rotateTo(angle = it.defaultAngle, time = 100.milliseconds) }
        isHitting = false
    }
}