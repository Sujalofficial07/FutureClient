package com.futureclient.module;

import com.futureclient.FutureClient;
import com.futureclient.config.Setting;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all client modules
 */
public abstract class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();
    private final String name;
    private final String description;
    private final Category category;
    private final List<Setting> settings = new ArrayList<>();
    
    private boolean enabled = false;
    private int keyBind = GLFW.GLFW_KEY_UNKNOWN;
    
    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
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
        FutureClient.getInstance().getConfigManager().saveConfig();
    }
    
    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
    public void onRender2D(float tickDelta) {}
    public void onRender3D(float tickDelta) {}
    
    protected void addSetting(Setting setting) {
        settings.add(setting);
    }
    
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    public int getKeyBind() { return keyBind; }
    public void setKeyBind(int key) { this.keyBind = key; }
    public List<Setting> getSettings() { return settings; }
    
    public enum Category {
        COMBAT("Combat"),
        VISUAL("Visual"),
        MOVEMENT("Movement"),
        PLAYER("Player"),
        RENDER("Render"),
        HUD("HUD");
        
        private final String name;
        
        Category(String name) {
            this.name = name;
        }
        
        public String getName() { return name; }
    }
}
