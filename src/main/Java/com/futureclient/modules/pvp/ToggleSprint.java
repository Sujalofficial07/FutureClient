package com.futureclient.modules.pvp;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import org.lwjgl.input.Keyboard;

public class ToggleSprint extends Module {
    public ToggleSprint() {
        super("ToggleSprint", Category.PVP, Keyboard.KEY_I);
        setToggled(true);
    }

    @Override
    public void onUpdate() {
        if (mc.thePlayer != null && 
            mc.thePlayer.moveForward > 0 && 
            !mc.thePlayer.isSneaking() && 
            !mc.thePlayer.isCollidedHorizontally &&
            mc.thePlayer.getFoodStats().getFoodLevel() > 6) {
            
            mc.thePlayer.setSprinting(true);
        }
    }
}
