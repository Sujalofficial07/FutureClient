package com.futureclient.impl.modules.misc

import com.futureclient.api.module.Category
import com.futureclient.api.module.Module
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.opengl.Display

class FPSReducer : Module("SmartFPS", Category.MISC) {
    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if (!Display.isActive()) {
            mc.gameSettings.limitFramerate = 5
        } else if (mc.gameSettings.limitFramerate == 5) {
            mc.gameSettings.limitFramerate = 120 
        }
    }
}

class ParticleOptimizations : Module("NoParticles", Category.RENDER) {
    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        // Simple logic to keep particle count low
        if (mc.effectRenderer.statistics.toLong() > 200) { 
             // Logic to clear particles would require ATs or reflection in strict forge
             // Placeholder: Users can expand this to invoke clearEffects()
        }
    }
}
