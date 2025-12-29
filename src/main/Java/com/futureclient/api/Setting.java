package com.futureclient.api;

// Simple setting system for future expansion
public class Setting<T> {
    public String name;
    public T value;
    public Module parent;

    public Setting(String name, Module parent, T value) {
        this.name = name;
        this.parent = parent;
        this.value = value;
    }
}
