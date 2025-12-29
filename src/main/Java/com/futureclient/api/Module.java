package com.futureclient.api;

import net.minecraft.client.Minecraft;

public abstract class Module {
    protected Minecraft mc = Minecraft.getMinecraft();
    private String name;
    private Category category;
    private boolean toggled;
    private int key; // LWJGL Key ID

    public Module(String name, Category category, int key) {
        this.name = name;
        this.category = category;
        this.key = key;
        this.toggled = false;
    }

    public void toggle() {
        this.toggled = !this.toggled;
        if (this.toggled) onEnable();
        else onDisable();
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        if (this.toggled) onEnable();
        else onDisable();
    }

    public boolean isToggled() { return toggled; }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }

    // Lifecycle methods
    public void onEnable() {}
    public void onDisable() {}
    public void onUpdate() {}
    public void onRender2D() {}
}
