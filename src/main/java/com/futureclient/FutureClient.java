package com.futureclient;

import com.futureclient.config.ConfigManager;
import com.futureclient.module.ModuleManager;
import com.futureclient.gui.GuiManager;
import com.futureclient.event.EventBus;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FutureClient - Modern Fabric PvP Client for Minecraft 1.20.1
 * Lunar Client-style experience with 1.8.9 PvP feel
 */
public class FutureClient implements ModInitializer {
    public static final String MOD_ID = "futureclient";
    public static final String MOD_NAME = "FutureClient";
    public static final String VERSION = "1.0.0";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    
    private static FutureClient instance;
    private ModuleManager moduleManager;
    private ConfigManager configManager;
    private GuiManager guiManager;
    private EventBus eventBus;
    
    @Override
    public void onInitialize() {
        instance = this;
        LOGGER.info("Initializing {} v{}", MOD_NAME, VERSION);
        
        // Initialize core systems
        eventBus = new EventBus();
        configManager = new ConfigManager();
        moduleManager = new ModuleManager();
        guiManager = new GuiManager();
        
        // Load configuration
        configManager.loadConfig();
        
        // Initialize modules
        moduleManager.initialize();
        
        LOGGER.info("{} initialized successfully!", MOD_NAME);
    }
    
    public static FutureClient getInstance() {
        return instance;
    }
    
    public ModuleManager getModuleManager() {
        return moduleManager;
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
    
    public GuiManager getGuiManager() {
        return guiManager;
    }
    
    public EventBus getEventBus() {
        return eventBus;
    }
}
