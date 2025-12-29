package com.futureclient.impl.modules.render

import com.futureclient.api.module.Category
import com.futureclient.api.module.Module
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

class Fullbright : Module("Fullbright", Category.RENDER) {
    private var originalGamma = 1f

    override fun onEnable() {
        super.onEnable()
        originalGamma = mc.gameSettings.gammaSetting
        mc.gameSettings.gammaSetting = 100f
    }

    override fun onDisable() {
        super.onDisable()
        mc.gameSettings.gammaSetting = originalGamma
    }
    
    // Periodically enforce it in case settings menu resets it
    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if (toggled && mc.gameSettings.gammaSetting != 100f) {
            mc.gameSettings.gammaSetting = 100f
        }
    }
}
