package model.actions

import model.Position
import model.items.Item

class ItemThrown(val item: Item, val position: Position) : Action
