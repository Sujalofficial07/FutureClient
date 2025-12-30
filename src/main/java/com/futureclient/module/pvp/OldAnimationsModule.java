package com.futureclient.module.pvp;

import com.futureclient.module.Category;
import com.futureclient.module.Module;

public class OldAnimationsModule extends Module {
    public static boolean ENABLED = false;
    
    public OldAnimationsModule() {
        super("Old Animations", "1.8.9 style animations", Category.PVP);
    }
    
    @Override
    public void onEnable() {
        ENABLED = true;
    }
    
    @Override
    public void onDisable() {
        ENABLED = false;
    }
}
