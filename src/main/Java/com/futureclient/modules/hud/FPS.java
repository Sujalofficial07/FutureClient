package com.futureclient.modules.hud;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import net.minecraft.client.Minecraft;

public class FPS extends Module {
    public FPS() {
        super("FPS", Category.HUD, 0);
        setToggled(true);
    }

    @Override
    public void onRender2D() {
        String text = "FPS: " + Minecraft.getDebugFPS();
        mc.fontRendererObj.drawStringWithShadow(text, 2, 2, -1);
    }
}
