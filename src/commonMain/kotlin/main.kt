import com.soywiz.klock.*
import com.soywiz.kmem.*
import com.soywiz.korev.*
import com.soywiz.korge.*
import com.soywiz.korge.tiled.*
import com.soywiz.korge.view.*
import com.soywiz.korio.file.std.*
import kotlinx.coroutines.runBlocking
import view.TileAnimation
import kotlin.math.pow

val tileSize = 16
val mapWidth = 32
val mapHeight = 32


suspend fun main() = Korge(width = tileSize * mapWidth + 10, height = tileSize * mapHeight + 10) {
    val camera = this.camera()
    val mapContainer = camera.container()
    val map = view.Map(mapContainer, mapWidth, mapHeight)
    val hero = view.Hero(mapContainer)
    hero.changePosition(16, 16)


}