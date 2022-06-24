package com.hse.sd.roguelike.view.sprites

import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

/**
 * Объект для загрузки текстур
 */
object TileAnimation {

    private val tileSet = runBlocking {
        resourcesVfs["0x72_DungeonTilesetII_v1.4/0x72_DungeonTilesetII_v1.4.png"].readBitmap()
    }

    private data class SpritePosition(
        val spriteWidth: Int,
        val spriteHeight: Int,
        val marginTop: Int,
        val marginLeft: Int,
        val columns: Int = 1
    )

    private val spriteNameToPosition = runBlocking {
        resourcesVfs["0x72_DungeonTilesetII_v1.4/tiles_list_v1.4"]
            .readLines()
            .filter { it.isNotEmpty() }
            .map { str -> str.split(' ').filter { it.isNotEmpty() } }
            .map { s ->
                val columns = if (s.size > 5) s[5].toInt() else 1
                s[0] to SpritePosition(s[3].toInt(), s[4].toInt(), s[2].toInt(), s[1].toInt(), columns)
            }
            .toMap()
    }

    fun getAnimation(name: String) = SpriteAnimation(
        spriteMap = tileSet,
        spriteWidth = spriteNameToPosition[name]!!.spriteWidth,
        spriteHeight = spriteNameToPosition[name]!!.spriteHeight,
        marginTop = spriteNameToPosition[name]!!.marginTop,
        marginLeft = spriteNameToPosition[name]!!.marginLeft,
        columns = spriteNameToPosition[name]!!.columns
    )

    object UI {
        val FullHeart = getAnimation("ui_heart_full")
        val HalfHeart = getAnimation("ui_heart_half")
        val EmptyHeart = getAnimation("ui_heart_empty")

        val RedButtonOff = getAnimation("red_button_off")
        val BlueButtonOff = getAnimation("blue_button_off")
        val Transparent = getAnimation("transparent")
    }

    object Map {
        fun getFloor() = getAnimation("floor_${Random.nextInt(1, 4)}")

        val Crate = getAnimation("crate")
        val Wall = getAnimation("wall_mid")
    }

    object Characters {
        val Knight = CharacterSprite(
            getAnimation("knight_f_idle_anim"),
            getAnimation("knight_f_run_anim"),
        )

        val OrcWarrior = CharacterSprite(
            getAnimation("orc_warrior_idle_anim"),
            getAnimation("orc_warrior_run_anim"),
        )

        val MaskedOrc = CharacterSprite(
            getAnimation("masked_orc_idle_anim"),
            getAnimation("masked_orc_run_anim"),
        )

        val Wizard = CharacterSprite(
            getAnimation("wizzard_f_idle_anim"),
            getAnimation("wizzard_f_run_anim"),
        )

        val Swampy = CharacterSprite(
            getAnimation("swampy_idle_anim"),
            getAnimation("swampy_run_anim"),
        )

        val Skeleton = CharacterSprite(
            getAnimation("skelet_idle_anim"),
            getAnimation("skelet_run_anim"),
        )

        val Zombie = CharacterSprite(
            getAnimation("zombie_idle_anim"),
            getAnimation("zombie_run_anim"),
        )

        val BigZombie = CharacterSprite(
            getAnimation("big_zombie_idle_anim"),
            getAnimation("big_zombie_run_anim"),
        )

        val Necromancer = CharacterSprite(
            getAnimation("necromancer_idle_anim"),
            getAnimation("necromancer_run_anim"),
        )

        val Lizard = CharacterSprite(
            getAnimation("lizard_m_idle_anim"),
            getAnimation("lizard_m_run_anim"),
        )

        val Chort = CharacterSprite(
            getAnimation("chort_idle_anim"),
            getAnimation("chort_run_anim"),
        )
    }

    object Weapons {
        val RegularSword = Weapon(getAnimation("weapon_regular_sword"))
        val Hammer = Weapon(getAnimation("weapon_hammer"))
        val Axe = Weapon(getAnimation("weapon_axe"))
        val MagicStick = Weapon(getAnimation("weapon_red_magic_staff"))
    }
}