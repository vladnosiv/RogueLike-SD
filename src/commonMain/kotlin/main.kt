import com.soywiz.korev.Key
import com.soywiz.korge.*
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.*
import kotlinx.coroutines.*

val tileSize  = 16
val mapHeight = 32
val mapWidth  = 32
val scale     = 1.5


suspend fun main() = Korge(
    title = "Rogue-like",
    height = (tileSize * mapHeight * scale).toInt(),
    width = (tileSize * mapWidth * scale).toInt(),
    virtualHeight = tileSize * mapHeight,
    virtualWidth = tileSize * mapWidth
) {
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
        // ʘ‿ʘ
        down(Key.N1) { keyboardHandler.onCommand(controller.Command.SELECT_ITEM0) }
        down(Key.N2) { keyboardHandler.onCommand(controller.Command.SELECT_ITEM1) }
        down(Key.N3) { keyboardHandler.onCommand(controller.Command.SELECT_ITEM2) }
        down(Key.N4) { keyboardHandler.onCommand(controller.Command.SELECT_ITEM3) }
        down(Key.N5) { keyboardHandler.onCommand(controller.Command.SELECT_ITEM4) }
        down(Key.SPACE) { keyboardHandler.onCommand(controller.Command.ATTACK) }
        down(Key.Q) { keyboardHandler.onCommand(controller.Command.THROW) }
        down(Key.E) { keyboardHandler.onCommand(controller.Command.PICK_UP) }
    }

    launch {
        while (true) {
            game.tick()
            delay(16)
        }
    }.join()
}