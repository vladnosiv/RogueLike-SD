import com.soywiz.korev.Key
import com.soywiz.korge.*
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.*
import kotlinx.coroutines.*

val tileSize = 16
val mapWidth = 32
val mapHeight = 32


suspend fun main() = Korge(width = tileSize * mapWidth, height = tileSize * mapHeight) {
    val camera          = this.camera()
    val ui              = view.UI(camera)
    val modelHandler    = model.ModelHandler()
    val keyboardHandler = controller.KeyboardHandler()
    val game            = controller.Game(ui, modelHandler, keyboardHandler)

    this.keys {
        down(Key.RIGHT) { keyboardHandler.onCommand(controller.Command.MOVE_RIGHT) }
        down(Key.LEFT)  { keyboardHandler.onCommand(controller.Command.MOVE_LEFT) }
        down(Key.UP)    { keyboardHandler.onCommand(controller.Command.MOVE_UP) }
        down(Key.DOWN)  { keyboardHandler.onCommand(controller.Command.MOVE_DOWN) }
        down(Key.SPACE) { keyboardHandler.onCommand(controller.Command.ATTACK) }
    }

    val job = launch {
        while (true) {
            game.tick()
            delay(16)
        }
    }
    job.join()
}