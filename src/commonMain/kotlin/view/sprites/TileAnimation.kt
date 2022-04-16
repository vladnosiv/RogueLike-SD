package view.sprites

import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.runBlocking

object TileAnimation {

    private val tileSet = runBlocking {
        resourcesVfs["0x72_DungeonTilesetII_v1.4/0x72_DungeonTilesetII_v1.4.png"].readBitmap()
    }

    private data class SpritePosition(val spriteWidth: Int,
                                      val spriteHeight: Int,
                                      val marginTop: Int,
                                      val marginLeft: Int,
                                      val columns: Int = 1)

    private val spriteNameToPosition = runBlocking {
        resourcesVfs["0x72_DungeonTilesetII_v1.4/tiles_list_v1.4"]
            .readLines()
            .filter { it.isNotEmpty() }
            .map { str -> str.split(' ').filter { it.isNotEmpty() } }
            .map { s ->
                val columns = if (s.size > 5) s[5].toInt() else 1
                s[0] to SpritePosition(s[3].toInt(), s[4].toInt(), s[2].toInt(), s[1].toInt(), columns) }
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

    object Map {
        val floor = getAnimation("floor_1")
        val wall = getAnimation("wall_mid")
    }

    object Characters {
        val Knight = CharacterSprite(
            getAnimation("knight_f_idle_anim"),
            getAnimation("knight_f_run_anim")
        )

        val Wizard = CharacterSprite(
            getAnimation("wizzard_f_idle_anim"),
            getAnimation("wizzard_f_run_anim")
        )
    }

    object Weapons {
        val RegularSword = Weapon(getAnimation("weapon_regular_sword"))
        val Hammer = Weapon(getAnimation("weapon_hammer"))
    }
}