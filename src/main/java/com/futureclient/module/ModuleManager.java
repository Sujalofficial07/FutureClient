package com.futureclient.module;

import com.futureclient.module.hud.*;
import com.futureclient.module.pvp.*;
import com.futureclient.module.render.*;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();
    
    public ModuleManager() {
        // HUD Modules
        registerModule(new KeystrokesModule());
        registerModule(new CPSModule());
        registerModule(new FPSModule());
        registerModule(new PingModule());
        registerModule(new CoordinatesModule());
        
        // PvP Modules
        registerModule(new ToggleSprintModule());
        registerModule(new OldAnimationsModule());
        registerModule(new CrosshairModule());
        
        // Render Modules
        registerModule(new ZoomModule());
        registerModule(new ParticleControlModule());
        registerModule(new EntityCullingModule());
    }
    
    private void registerModule(Module module) {
        modules.add(module);
    }
    
    public void onTick() {
        modules.stream()
            .filter(Module::isEnabled)
            .forEach(Module::onTick);
    }
    
    public void onRender(DrawContext context, float tickDelta) {
        modules.stream()
            .filter(Module::isEnabled)
            .forEach(module -> module.onRender(context, tickDelta));
    }
    
    public List<Module> getModules() {
        return modules;
    }
    
    public List<Module> getModulesByCategory(Category category) {
        return modules.stream()
            .filter(module -> module.getCategory() == category)
            .collect(Collectors.toList());
    }
    
    public Module getModule(String name) {
        return modules.stream()
            .filter(module -> module.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
}
