package com.futureclient.impl.modules.combat

import com.futureclient.api.module.Category
import com.futureclient.api.module.Module
import com.futureclient.api.module.NumberSetting
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class Velocity : Module("Velocity", Category.COMBAT) {
    // 0% means no knockback, 100% means full knockback
    private val horizontal = NumberSetting("Horizontal", 0.0, 0.0, 100.0, 1.0)
    private val vertical = NumberSetting("Vertical", 0.0, 0.0, 100.0, 1.0)

    init {
        addSetting(horizontal)
        addSetting(vertical)
    }

    @SubscribeEvent
    fun onLivingUpdate(event: LivingEvent.LivingUpdateEvent) {
        // Forge 1.8.9 requires mixins for perfect Velocity, but we can reduce it via motion adjustment
        if (event.entityLiving == mc.thePlayer) {
            if (mc.thePlayer.hurtTime > 0 && mc.thePlayer.hurtResistantTime > 0) {
                 mc.thePlayer.motionX *= (horizontal.value / 100.0)
                 mc.thePlayer.motionZ *= (horizontal.value / 100.0)
                 mc.thePlayer.motionY *= (vertical.value / 100.0)
            }
        }
    }
}
