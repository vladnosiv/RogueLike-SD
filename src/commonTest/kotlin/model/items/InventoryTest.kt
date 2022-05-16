package model.items

import model.Position
import model.actors.MainCharacter
import model.actors.Mob
import model.Direction
import model.map.FloorMapGenerator
import model.map.MapGeneratorConfig
import kotlin.collections.Map
import kotlin.test.*

class InventoryTest {

    @Test
    fun testAdd() {
        val inv = Inventory()

        inv.addItem(Sword())

        assertEquals(inv.getItem(0).type, Sword().type)
    }

    @Test
    fun testThrow() {
        val inv = Inventory()
        inv.addItem(Sword())

        val map = FloorMapGenerator(MapGeneratorConfig(5, 5)).genMap()

        val pos = Position(2, 2)
        val hero = MainCharacter(pos, 100, 1, 100)

        inv.throwItem(0, hero, map)

        assertEquals(ItemType.NULL, inv.getItem(0).type)
    }
}
