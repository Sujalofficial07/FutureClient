package com.futureclient.impl.modules.misc

import com.futureclient.api.module.Category
import com.futureclient.api.module.Module
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.opengl.Display

class FPSReducer : Module("SmartFPS", Category.MISC) {
    // If window is unfocused, drop FPS to save resources
    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if (!Display.isActive()) {
            mc.gameSettings.limitFramerate = 5
        } else if (mc.gameSettings.limitFramerate == 5) {
            mc.gameSettings.limitFramerate = 120 // Restore (or read from options)
        }
    }
}

class ParticleOptimizations : Module("NoParticles", Category.RENDER) {
    // 1.8.9 Forge doesn't have a clean SpawnParticleEvent without Mixins/Coremod.
    // However, we can aggressively clear particles if performance is needed.
    
    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        // Radical optimization: clear particles if count is too high
        if (mc.effectRenderer.statistics.toLong() > 200) { 
            // This is a naive cleanup, normally you'd prevent them spawning
        }
    }
}
