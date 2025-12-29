package com.futureclient.modules.hud;

import com.futureclient.api.Category;
import com.futureclient.api.Module;

public class Coordinates extends Module {
    public Coordinates() {
        super("Coordinates", Category.HUD, 0);
    }

    @Override
    public void onRender2D() {
        String coords = String.format("XYZ: %.0f / %.0f / %.0f", mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
        mc.fontRendererObj.drawStringWithShadow(coords, 2, 12, -1);
    }
}
