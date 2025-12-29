package com.futureclient.api.module

import com.futureclient.impl.modules.combat.* // If you add combat modules later
import com.futureclient.impl.modules.hud.*
import com.futureclient.impl.modules.render.*
import com.futureclient.impl.modules.player.*
import com.futureclient.impl.modules.misc.*
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ModuleManager {
    private val modules = ArrayList<Module>()

    init {
        modules.add(Sprint())
        modules.add(Fullbright())
        modules.add(HUD())
        modules.add(Zoom())
        modules.add(FPSReducer())
        modules.add(ParticleOptimizations())
        modules.add(CustomMenuModule())
    }

    fun getModules(): List<Module> = modules
    
    fun getModulesByCategory(category: Category): List<Module> {
        return modules.filter { it.category == category }
    }

    @SubscribeEvent
    fun onRenderOverlay(event: RenderGameOverlayEvent.Post) {
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) return
        getModules().filter { it.toggled && it is HUD }.forEach { (it as HUD).renderHUD() }
    }
}
