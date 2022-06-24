package com.hse.sd.roguelike.model

import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actions.HeroChangedDirection
import com.hse.sd.roguelike.model.actions.HeroHPChanged
import com.hse.sd.roguelike.model.actions.MapChanged
import com.hse.sd.roguelike.model.actors.MainCharacter
import com.hse.sd.roguelike.model.actors.MainCharacterConfig
import com.hse.sd.roguelike.model.actors.mobs.AbstractMobFactory
import com.hse.sd.roguelike.model.actors.mobs.MobConfig
import com.hse.sd.roguelike.model.items.Item
import com.hse.sd.roguelike.model.items.Sword
import com.hse.sd.roguelike.model.map.MapGenerator

class EnvironmentBuilder(private val mobFactory: AbstractMobFactory) {
    private lateinit var generator: MapGenerator
    private lateinit var hero: MainCharacter
    private val mobConfigs = mutableListOf<MobConfig>()
    private val itemConfigs = mutableListOf<Pair<Position, Item>>()
    private val actions = mutableListOf<Action>()
    private var isGenerated = false

    fun registerGenerator(generator: MapGenerator): EnvironmentBuilder {
        this.generator = generator

        return this
    }

    fun addMainCharacter(config: MainCharacterConfig): EnvironmentBuilder {
        this.hero = MainCharacter(
            config.position, config.hp, config.power, config.exp
        )

        return this
    }

    fun addMobs(vararg configs: MobConfig): EnvironmentBuilder {
        mobConfigs.addAll(configs)
        return this
    }

    fun generate(): Environment {
        assert(!isGenerated)

        val map = generator.genMap()

        for ((pos, item) in itemConfigs) {
            actions.addAll(map.createItem(pos, item))
        }

        actions.let {
            it.addAll(hero.addItem(Sword()))
            it.addAll(hero.equip(0))
        }

        actions.addAll(map.createMainCharacter(hero.position, hero))
        actions.addAll(
            listOf(
                MapChanged(map.field),
                HeroChangedDirection(hero.direction),
                HeroHPChanged(hero.hp, hero.hp)
            )
        )

        val environment = Environment(map, this.hero)


        val mobs = mobConfigs.map { mobFactory.createMob(it, environment) }

        environment.mobs.addAll(mobs)

        for (mob in environment.mobs) {
            actions.addAll(map.createMob(mob.position, mob))
        }

        isGenerated = true

        return environment
    }

    fun addItems(vararg itemConfigs: Pair<Position, Item>): EnvironmentBuilder {
        this.itemConfigs.addAll(itemConfigs)

        return this
    }

    fun getActions(): List<Action> = actions
}