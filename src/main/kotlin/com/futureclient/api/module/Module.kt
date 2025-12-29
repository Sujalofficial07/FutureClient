package com.futureclient.api.module

import com.futureclient.FutureClient
import net.minecraft.client.Minecraft
import net.minecraftforge.common.MinecraftForge

enum class Category {
    COMBAT, RENDER, PLAYER, MISC, HUD
}

abstract class Module(val name: String, val category: Category) {
    protected val mc: Minecraft = Minecraft.getMinecraft()
    var toggled: Boolean = false
    var key: Int = 0
    val settings = ArrayList<Setting<*>>()

    fun toggle() {
        toggled = !toggled
        if (toggled) onEnable() else onDisable()
    }

    open fun onEnable() {
        MinecraftForge.EVENT_BUS.register(this)
    }

    open fun onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this)
    }

    // Helpers to add settings
    protected fun addSetting(setting: Setting<*>) {
        settings.add(setting)
    }
}
