package com.futureclient.impl.modules.player

import com.futureclient.api.module.Category
import com.futureclient.api.module.Module
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

class Sprint : Module("ToggleSprint", Category.PLAYER) {
    @SubscribeEvent
    fun onTick(event: TickEvent.PlayerTickEvent) {
        if (mc.thePlayer != null && 
            mc.thePlayer.movementInput.moveForward > 0 && 
            !mc.thePlayer.isSneaking && 
            !mc.thePlayer.isCollidedHorizontally && 
            mc.thePlayer.foodStats.foodLevel > 6) {
            mc.thePlayer.setSprinting(true)
        }
    }
}
