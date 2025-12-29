package com.futureclient.impl.modules.render

import com.futureclient.api.module.Category
import com.futureclient.api.module.Module
import com.futureclient.api.module.NumberSetting
import net.minecraftforge.client.event.FOVUpdateEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class Zoom : Module("Zoom", Category.RENDER) {
    val factor = NumberSetting("Factor", 4.0, 1.0, 10.0, 0.5)
    init { addSetting(factor) }

    @SubscribeEvent
    fun onFOV(event: FOVUpdateEvent) {
        event.newfov = event.fov / factor.value.toFloat()
    }
}
