package com.futureclient.module.render;

import com.futureclient.module.Category;
import com.futureclient.module.Module;
import net.minecraft.client.MinecraftClient;

public class ZoomModule extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private double originalFov;
    private double targetFov = 30.0;
    private double currentZoom = 1.0;
    private boolean zooming = false;
    
    public ZoomModule() {
        super("Zoom", "OptiFine-style zoom", Category.RENDER);
    }
    
    @Override
    public void onEnable() {
        if (mc.options == null) return;
        originalFov = mc.options.getFov().getValue();
        zooming = true;
    }
    
    @Override
    public void onDisable() {
        if (mc.options == null) return;
        mc.options.getFov().setValue(originalFov);
        zooming = false;
        currentZoom = 1.0;
    }
    
    @Override
    public void onTick() {
        if (mc.options == null) return;
        
        if (zooming) {
            // Smooth zoom in
            currentZoom += (0.1 - currentZoom) * 0.3;
            mc.options.getFov().setValue(originalFov * currentZoom + targetFov * (1 - currentZoom));
        }
    }
}
