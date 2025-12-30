package com.futureclient.module.pvp;

import com.futureclient.module.Category;
import com.futureclient.module.Module;
import net.minecraft.client.MinecraftClient;

public class ToggleSprintModule extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    
    public ToggleSprintModule() {
        super("Toggle Sprint", "Automatically sprint", Category.PVP);
    }
    
    @Override
    public void onTick() {
        if (mc.player == null) return;
        
        if (mc.player.forwardSpeed > 0 && !mc.player.isSneaking() && 
            !mc.player.isUsingItem() && !mc.player.horizontalCollision &&
            mc.player.getHungerManager().getFoodLevel() > 6) {
            mc.player.setSprinting(true);
        }
    }
}
