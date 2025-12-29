package com.futureclient.modules.pvp;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class Zoom extends Module {
    private boolean zoomed = false;

    public Zoom() {
        super("Zoom", Category.PVP, Keyboard.KEY_C);
        // Note: Logic is KeyBind based, not toggle based usually, but here we toggle on C press for demo logic
        // For actual Zoom, you usually check Keyboard.isKeyDown(KEY_C) in update or render events
        MinecraftForge.EVENT_BUS.register(this);
    }

    // This handles smooth FOV modification
    @SubscribeEvent
    public void onFOV(EntityViewRenderEvent.FOVModifier event) {
        if (Keyboard.isKeyDown(getKey())) {
            event.newfov = event.fov / 4.0F; // 4x Zoom
        }
    }
}
