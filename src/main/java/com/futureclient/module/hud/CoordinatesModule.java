package com.futureclient.module.hud;

import com.futureclient.module.Category;
import com.futureclient.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.BlockPos;

public class CoordinatesModule extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private int x = 10;
    private int y = 210;
    
    public CoordinatesModule() {
        super("Coordinates", "Display your coordinates", Category.HUD);
    }
    
    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (mc.player == null) return;
        
        BlockPos pos = mc.player.getBlockPos();
        String text = String.format("XYZ: %d, %d, %d", pos.getX(), pos.getY(), pos.getZ());
        
        context.drawTextWithShadow(mc.textRenderer, text, x, y, 0xFFFFFF);
    }
}
