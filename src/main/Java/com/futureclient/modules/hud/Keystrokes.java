package com.futureclient.modules.hud;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import com.futureclient.util.RenderUtil;
import net.minecraft.client.settings.KeyBinding;
import java.awt.Color;

public class Keystrokes extends Module {
    public Keystrokes() {
        super("Keystrokes", Category.HUD, 0);
    }

    @Override
    public void onRender2D() {
        int x = 5;
        int y = 40;
        int size = 20;
        int gap = 2;

        // W
        drawKey(mc.gameSettings.keyBindForward, x + size + gap, y, size);
        // A
        drawKey(mc.gameSettings.keyBindLeft, x, y + size + gap, size);
        // S
        drawKey(mc.gameSettings.keyBindBack, x + size + gap, y + size + gap, size);
        // D
        drawKey(mc.gameSettings.keyBindRight, x + size * 2 + gap * 2, y + size + gap, size);
    }

    private void drawKey(KeyBinding key, int x, int y, int size) {
        boolean pressed = key.isKeyDown();
        // Background
        RenderUtil.drawRect(x, y, x + size, y + size, pressed ? new Color(255, 255, 255, 100).getRGB() : new Color(0, 0, 0, 100).getRGB());
        // Text
        String keyName = org.lwjgl.input.Keyboard.getKeyName(key.getKeyCode());
        int textWidth = mc.fontRendererObj.getStringWidth(keyName);
        mc.fontRendererObj.drawStringWithShadow(keyName, x + (size - textWidth) / 2, y + (size - 8) / 2, pressed ? -1 : 0xAAAAAA);
    }
}
