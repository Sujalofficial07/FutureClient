package com.futureclient.module;

import com.futureclient.FutureClient;
import net.minecraft.client.gui.DrawContext;

public abstract class Module {
    private final String name;
    private final String description;
    private final Category category;
    private boolean enabled;
    private int keybind;
    
    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
        this.keybind = -1;
    }
    
    public void toggle() {
        setEnabled(!enabled);
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
        FutureClient.getInstance().getConfigManager().save();
    }
    
    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
    public void onRender(DrawContext context, float tickDelta) {}
    
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    public int getKeybind() { return keybind; }
    public void setKeybind(int keybind) { this.keybind = keybind; }
}
