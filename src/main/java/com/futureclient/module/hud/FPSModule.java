package com.futureclient.module.hud;

import com.futureclient.module.Category;
import com.futureclient.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class FPSModule extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private int x = 10;
    private int y = 170;
    
    public FPSModule() {
        super("FPS", "Display your FPS", Category.HUD);
    }
    
    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (mc.player == null) return;
        
        int fps = mc.getCurrentFps();
        String text = String.format("FPS: %d", fps);
        
        int color = fps >= 60 ? 0x00FF00 : fps >= 30 ? 0xFFFF00 : 0xFF0000;
        context.drawTextWithShadow(mc.textRenderer, text, x, y, color);
    }
}
