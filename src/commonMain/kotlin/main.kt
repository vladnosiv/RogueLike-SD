import com.soywiz.klock.*
import com.soywiz.kmem.*
import com.soywiz.korev.*
import com.soywiz.korge.*
import com.soywiz.korge.tiled.*
import com.soywiz.korge.view.*
import com.soywiz.korio.file.std.*
import kotlin.math.pow

val tileSize = 16
val mapWidth = 32
val mapHeight = 32


suspend fun main() = Korge(width = tileSize * mapWidth, height = tileSize * mapHeight) {
    val camera = this.camera()
    val mapContainer = camera.container()
    val characterContainer = camera.container()

}