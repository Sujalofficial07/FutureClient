package com.futureclient.module.hud;

import com.futureclient.module.Category;
import com.futureclient.module.Module;
import com.futureclient.util.CPSTracker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CPSModule extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private int x = 10;
    private int y = 150;
    
    public CPSModule() {
        super("CPS", "Display your clicks per second", Category.HUD);
    }
    
    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (mc.player == null) return;
        
        int leftCPS = CPSTracker.getLeftCPS();
        int rightCPS = CPSTracker.getRightCPS();
        
        String text = String.format("CPS: %d | %d", leftCPS, rightCPS);
        context.drawTextWithShadow(mc.textRenderer, text, x, y, 0xFFFFFF);
    }
}
