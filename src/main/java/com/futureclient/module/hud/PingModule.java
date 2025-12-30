package com.futureclient.module.hud;

import com.futureclient.module.Category;
import com.futureclient.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;

public class PingModule extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private int x = 10;
    private int y = 190;
    
    public PingModule() {
        super("Ping", "Display your ping", Category.HUD);
    }
    
    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (mc.player == null || mc.getNetworkHandler() == null) return;
        
        PlayerListEntry entry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
        if (entry == null) return;
        
        int ping = entry.getLatency();
        String text = String.format("Ping: %dms", ping);
        
        int color = ping <= 50 ? 0x00FF00 : ping <= 100 ? 0xFFFF00 : 0xFF0000;
        context.drawTextWithShadow(mc.textRenderer, text, x, y, color);
    }
}
