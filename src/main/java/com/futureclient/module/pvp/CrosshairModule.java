package com.futureclient.module.pvp;

import com.futureclient.module.Category;
import com.futureclient.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CrosshairModule extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private int color = 0xFFFFFF;
    private int size = 5;
    private int thickness = 1;
    
    public CrosshairModule() {
        super("Crosshair", "Custom crosshair", Category.PVP);
    }
    
    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (mc.player == null || mc.options.getPerspective().isFirstPerson() == false) return;
        
        int centerX = mc.getWindow().getScaledWidth() / 2;
        int centerY = mc.getWindow().getScaledHeight() / 2;
        
        // Draw crosshair
        context.fill(centerX - size, centerY - thickness / 2, centerX + size, centerY + thickness / 2, color);
        context.fill(centerX - thickness / 2, centerY - size, centerX + thickness / 2, centerY + size, color);
    }
    
    public void setColor(int color) { this.color = color; }
    public void setSize(int size) { this.size = size; }
    public void setThickness(int thickness) { this.thickness = thickness; }
}
