package com.futureclient.modules.hud;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import org.lwjgl.input.Mouse;
import java.util.ArrayList;
import java.util.List;

public class CPS extends Module {
    private List<Long> clicks = new ArrayList<Long>();
    private boolean wasPressed;

    public CPS() {
        super("CPS", Category.HUD, 0);
    }

    @Override
    public void onUpdate() {
        boolean pressed = Mouse.isButtonDown(0);
        if (pressed && !wasPressed) {
            clicks.add(System.currentTimeMillis());
        }
        wasPressed = pressed;

        // Remove old clicks (> 1000ms ago)
        long time = System.currentTimeMillis();
        clicks.removeIf(timestamp -> timestamp + 1000 < time);
    }

    @Override
    public void onRender2D() {
        mc.fontRendererObj.drawStringWithShadow("CPS: " + clicks.size(), 2, 22, -1);
    }
}
