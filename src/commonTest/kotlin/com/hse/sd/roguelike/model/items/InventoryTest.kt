package com.hse.sd.roguelike.model.items

import com.hse.sd.roguelike.model.Position
import com.hse.sd.roguelike.model.actors.MainCharacter
import com.hse.sd.roguelike.model.map.FloorMapGenerator
import com.hse.sd.roguelike.model.map.MapGeneratorConfig
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

        val map = FloorMapGenerator(MapGeneratorConfig(5, 5)).generateMap()

        val pos = Position(2, 2)
        val hero = MainCharacter(pos, 100, 1, 100)

        inv.throwItem(0, hero, map)

        assertEquals(ItemType.NULL, inv.getItem(0).type)
    }
}
