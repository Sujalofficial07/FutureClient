package com.futureclient.api.module

import com.futureclient.impl.modules.combat.*
import com.futureclient.impl.modules.hud.*
import com.futureclient.impl.modules.render.*
import com.futureclient.impl.modules.player.*
import com.futureclient.impl.modules.misc.*
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ModuleManager {
    private val modules = ArrayList<Module>()

    init {
        // Register Modules Here
        modules.add(Sprint())
        modules.add(Fullbright())
        modules.add(HUD())
        modules.add(Zoom())
        modules.add(FPSReducer()) // Smart FPS
        modules.add(ParticleOptimizations())
        modules.add(CustomMenuModule())
    }

    fun getModules(): List<Module> = modules
    
    fun getModulesByCategory(category: Category): List<Module> {
        return modules.filter { it.category == category }
    }

    fun getModule(clazz: Class<out Module>): Module? {
        return modules.find { it::class.java == clazz }
    }

    @SubscribeEvent
    fun onRenderOverlay(event: RenderGameOverlayEvent.Post) {
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return
        // Hook for HUD rendering
        getModules().filter { it.toggled && it is HUD }.forEach { (it as HUD).renderHUD() }
    }
}
