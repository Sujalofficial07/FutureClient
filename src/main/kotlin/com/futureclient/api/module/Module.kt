package com.futureclient.api.module

import net.minecraft.client.Minecraft
import net.minecraftforge.common.MinecraftForge

enum class Category {
    COMBAT, RENDER, PLAYER, MISC, HUD
}

abstract class Setting<T>(val name: String, var value: T)
class BooleanSetting(name: String, value: Boolean) : Setting<Boolean>(name, value)
class NumberSetting(name: String, value: Double, val min: Double, val max: Double, val increment: Double) : Setting<Double>(name, value)

abstract class Module(val name: String, val category: Category) {
    protected val mc: Minecraft = Minecraft.getMinecraft()
    var toggled: Boolean = false
    var key: Int = 0
    val settings = ArrayList<Setting<*>>()

    fun toggle() {
        toggled = !toggled
        if (toggled) onEnable() else onDisable()
    }

    open fun onEnable() { MinecraftForge.EVENT_BUS.register(this) }
    open fun onDisable() { MinecraftForge.EVENT_BUS.unregister(this) }
    protected fun addSetting(setting: Setting<*>) { settings.add(setting) }
}
