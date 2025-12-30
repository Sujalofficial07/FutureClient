package com.futureclient.module;

import com.futureclient.FutureClient;
import com.futureclient.module.modules.combat.*;
import com.futureclient.module.modules.visual.*;
import com.futureclient.module.modules.movement.*;
import com.futureclient.module.modules.hud.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();
    
    public void initialize() {
        // Combat modules
        register(new OldAnimations());
        register(new HitFeedback());
        
        // Visual modules
        register(new CustomCrosshair());
        register(new ParticleControl());
        register(new CleanView());
        
        // Movement modules
        register(new ToggleSprint());
        register(new Zoom());
        
        // HUD modules
        register(new KeystrokesHUD());
        register(new CPSCounter());
        register(new FPSDisplay());
        register(new PingDisplay());
        register(new CoordinatesHUD());
        
        FutureClient.LOGGER.info("Loaded {} modules", modules.size());
    }
    
    private void register(Module module) {
        modules.add(module);
    }
    
    public List<Module> getModules() {
        return modules;
    }
    
    public List<Module> getModulesByCategory(Module.Category category) {
        return modules.stream()
            .filter(m -> m.getCategory() == category)
            .toList();
    }
    
    public Optional<Module> getModule(String name) {
        return modules.stream()
            .filter(m -> m.getName().equalsIgnoreCase(name))
            .findFirst();
    }
    
    public void onTick() {
        modules.stream()
            .filter(Module::isEnabled)
            .forEach(Module::onTick);
    }
    
    public void onRender2D(float tickDelta) {
        modules.stream()
            .filter(Module::isEnabled)
            .forEach(m -> m.onRender2D(tickDelta));
    }
    
    public void onRender3D(float tickDelta) {
        modules.stream()
            .filter(Module::isEnabled)
            .forEach(m -> m.onRender3D(tickDelta));
    }
}
