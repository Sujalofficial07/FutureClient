package com.futureclient.module.render;

import com.futureclient.module.Category;
import com.futureclient.module.Module;

public class ParticleControlModule extends Module {
    public static boolean ENABLED = false;
    public static int multiplier = 50; // Percentage
    
    public ParticleControlModule() {
        super("Particle Control", "Reduce particle count", Category.RENDER);
    }
    
    @Override
    public void onEnable() {
        ENABLED = true;
    }
    
    @Override
    public void onDisable() {
        ENABLED = false;
    }
    
    public static void setMultiplier(int value) {
        multiplier = Math.max(0, Math.min(100, value));
    }
}
