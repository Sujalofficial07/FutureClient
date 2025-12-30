package com.futureclient;

import com.futureclient.config.ConfigManager;
import com.futureclient.event.EventBus;
import com.futureclient.module.ModuleManager;
import com.futureclient.util.KeybindManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FutureClient implements ClientModInitializer {
    public static final String MOD_ID = "futureclient";
    public static final String MOD_NAME = "FutureClient";
    public static final String VERSION = "1.0.0";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    
    private static FutureClient INSTANCE;
    private ModuleManager moduleManager;
    private ConfigManager configManager;
    private EventBus eventBus;
    private KeybindManager keybindManager;
    
    public static KeyBinding GUI_KEY;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;
        
        LOGGER.info("Initializing {} v{}", MOD_NAME, VERSION);
        
        // Initialize core systems
        eventBus = new EventBus();
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();
        keybindManager = new KeybindManager();
        
        // Register GUI keybind
        GUI_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.futureclient.gui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.futureclient"
        ));
        
        // Load configuration
        configManager.load();
        
        // Register event listeners
        registerEventListeners();
        
        LOGGER.info("{} initialized successfully!", MOD_NAME);
    }
    
    private void registerEventListeners() {
        // Client tick event for keybinds and module updates
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            
            // Check GUI keybind
            if (GUI_KEY.wasPressed()) {
                client.setScreen(new com.futureclient.gui.ClientMenuScreen());
            }
            
            // Update modules
            moduleManager.onTick();
            keybindManager.onTick();
        });
        
        // HUD render event
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            moduleManager.onRender(drawContext, tickDelta);
        });
    }
    
    public static FutureClient getInstance() {
        return INSTANCE;
    }
    
    public ModuleManager getModuleManager() {
        return moduleManager;
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
    
    public EventBus getEventBus() {
        return eventBus;
    }
    
    public KeybindManager getKeybindManager() {
        return keybindManager;
    }
}
