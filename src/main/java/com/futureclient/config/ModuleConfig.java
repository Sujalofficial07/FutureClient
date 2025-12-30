package com.futureclient.config;

public class ModuleConfig {
    public boolean enabled;
    public int keybind;
    
    public ModuleConfig() {
        this.enabled = false;
        this.keybind = -1;
    }
    
    public ModuleConfig(boolean enabled, int keybind) {
        this.enabled = enabled;
        this.keybind = keybind;
    }
}
