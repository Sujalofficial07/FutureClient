package com.futureclient.modules.render;

import com.futureclient.api.Category;
import com.futureclient.api.Module;

public class Fullbright extends Module {
    private float oldGamma;

    public Fullbright() {
        super("Fullbright", Category.RENDER, 0);
    }

    @Override
    public void onEnable() {
        oldGamma = mc.gameSettings.gammaSetting;
        mc.gameSettings.gammaSetting = 100f;
    }

    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = oldGamma;
    }
}
