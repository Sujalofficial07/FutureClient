package com.futureclient.module.hud;

import com.futureclient.module.Category;
import com.futureclient.module.Module;
import com.futureclient.render.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.GameOptions;
import org.lwjgl.glfw.GLFW;

public class KeystrokesModule extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private int x = 10;
    private int y = 10;
    private static final int KEY_SIZE = 30;
    private static final int SPACING = 2;
    
    public KeystrokesModule() {
        super("Keystrokes", "Display your key presses", Category.HUD);
    }
    
    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (mc.player == null) return;
        
        GameOptions options = mc.options;
        
        // W key
        drawKey(context, x + KEY_SIZE + SPACING, y, "W", options.forwardKey.isPressed());
        
        // A key
        drawKey(context, x, y + KEY_SIZE + SPACING, "A", options.leftKey.isPressed());
        
        // S key
        drawKey(context, x + KEY_SIZE + SPACING, y + KEY_SIZE + SPACING, "S", options.backKey.isPressed());
        
        // D key
        drawKey(context, x + (KEY_SIZE + SPACING) * 2, y + KEY_SIZE + SPACING, "D", options.rightKey.isPressed());
        
        // LMB
        drawKey(context, x, y + (KEY_SIZE + SPACING) * 2, "LMB", mc.options.attackKey.isPressed());
        
        // RMB
        drawKey(context, x + (KEY_SIZE + SPACING) * 2, y + (KEY_SIZE + SPACING) * 2, "RMB", mc.options.useKey.isPressed());
    }
    
    private void drawKey(DrawContext context, int x, int y, String text, boolean pressed) {
        int color = pressed ? 0x90FFFFFF : 0x60000000;
        int textColor = pressed ? 0xFFFFFF00 : 0xFFFFFFFF;
        
        RenderUtil.drawRoundedRect(context, x, y, KEY_SIZE, KEY_SIZE, 3, color);
        
        int textWidth = mc.textRenderer.getWidth(text);
        int textX = x + (KEY_SIZE - textWidth) / 2;
        int textY = y + (KEY_SIZE - 8) / 2;
        
        context.drawText(mc.textRenderer, text, textX, textY, textColor, true);
    }
}
