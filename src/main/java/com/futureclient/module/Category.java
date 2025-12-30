package com.futureclient.module;

public enum Category {
    HUD("HUD"),
    PVP("PvP"),
    RENDER("Render"),
    MOVEMENT("Movement"),
    MISC("Misc");
    
    private final String displayName;
    
    Category(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
