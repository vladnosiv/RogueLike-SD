package model.actions

import model.Position
import model.items.Item

class ItemCreated(val pos: Position, val item: Item) : Action
