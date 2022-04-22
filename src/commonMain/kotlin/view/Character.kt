package view

import com.soywiz.klock.milliseconds
import com.soywiz.kmem.toInt
import com.soywiz.korge.animate.*
import com.soywiz.korge.view.*
import com.soywiz.korge.view.tween.moveTo
import com.soywiz.korge.view.tween.rotateTo
import com.soywiz.korma.geom.*
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

    private var currentDirection = Direction.RIGHT

    private var x = 0
    private var y = 0

    @Volatile private var isMoving = false

    fun setWeapon(newWeapon: Weapon) {
        if (!isMoving) {
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
            weapon!!.getWeaponPosition(x, y, currentDirection).first,
            weapon!!.getWeaponPosition(x, y, currentDirection).second
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
                    weapon!!.getWeaponPosition(x, y, currentDirection).first,
                    weapon!!.getWeaponPosition(x, y, currentDirection).second,
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
        if (isMoving) {
            return
        }
        isMoving = true

        val wAngle = weaponSprite?.rotation
        val wX = weapon?.getWeaponPosition!!.invoke(x, y, currentDirection).first
        val wY = weapon?.getWeaponPosition!!.invoke(x, y, currentDirection).second
        when (currentDirection) {
            Direction.UP_LEFT, Direction.UP_RIGHT -> {
                weapon?.let { weaponSprite!!.moveTo(wX, wY - it.hitDistance, time = 100.milliseconds) }
                weapon?.let { weaponSprite!!.moveTo(wX, wY, time = 100.milliseconds) }
            }
            Direction.DOWN_LEFT, Direction.DOWN_RIGHT -> {
                weapon?.let { weaponSprite!!.moveTo(wX, wY + it.hitDistance, time = 100.milliseconds) }
                weapon?.let { weaponSprite!!.moveTo(wX, wY, time = 100.milliseconds) }
            }
            Direction.LEFT -> {
                weapon?.let { weaponSprite!!.rotateTo(angle = wAngle!! - it.hitAngle, time = 150.milliseconds) }
                weapon?.let { weaponSprite!!.rotateTo(angle = wAngle!!, time = 100.milliseconds) }
            }
            Direction.RIGHT -> {
                weapon?.let { weaponSprite!!.rotateTo(angle = wAngle!! + it.hitAngle, time = 150.milliseconds) }
                weapon?.let { weaponSprite!!.rotateTo(angle = wAngle!!, time = 100.milliseconds) }
            }
        }

        isMoving = false
    }

    fun changeDirection(direction: Direction) {
        var direction = when (direction) {
            Direction.UP -> when (currentDirection) {
                Direction.LEFT       -> Direction.UP_LEFT
                Direction.RIGHT      -> Direction.UP_RIGHT
                Direction.DOWN_LEFT  -> Direction.UP_LEFT
                Direction.DOWN_RIGHT -> Direction.UP_RIGHT
                else                 -> currentDirection
            }
            Direction.DOWN -> when (currentDirection) {
                Direction.LEFT     -> Direction.DOWN_LEFT
                Direction.RIGHT    -> Direction.DOWN_RIGHT
                Direction.UP_LEFT  -> Direction.DOWN_LEFT
                Direction.UP_RIGHT -> Direction.DOWN_RIGHT
                else               -> currentDirection
            }
            else -> direction
        }

        if (currentDirection.side() != direction.side()) {
            characterSprite.skewY += (180).degrees
        }

        weaponSprite?.rotation(
            weapon!!.getWeaponAngle(direction)
        )
        weaponSprite?.position(
            weapon!!.getWeaponPosition(x, y, direction).first,
            weapon!!.getWeaponPosition(x, y, direction).second
        )

        currentDirection = direction
    }
}