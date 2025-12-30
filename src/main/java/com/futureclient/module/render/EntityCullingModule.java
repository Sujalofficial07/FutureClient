package com.futureclient.module.render;

import com.futureclient.module.Category;
import com.futureclient.module.Module;

public class EntityCullingModule extends Module {
    public static boolean ENABLED = false;
    
    public EntityCullingModule() {
        super("Entity Culling", "Don't render entities outside view", Category.RENDER);
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
