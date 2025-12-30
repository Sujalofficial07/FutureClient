package com.futureclient.config;

import com.futureclient.FutureClient;
import com.futureclient.module.Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Path configDir;
    private final File configFile;
    
    public ConfigManager() {
        configDir = FabricLoader.getInstance().getConfigDir().resolve(FutureClient.MOD_ID);
        configFile = configDir.resolve("config.json").toFile();
        
        try {
            Files.createDirectories(configDir);
        } catch (IOException e) {
            FutureClient.LOGGER.error("Failed to create config directory", e);
        }
    }
    
    public void save() {
        try (FileWriter writer = new FileWriter(configFile)) {
            JsonObject root = new JsonObject();
            JsonObject modules = new JsonObject();
            
            for (Module module : FutureClient.getInstance().getModuleManager().getModules()) {
                JsonObject moduleObj = new JsonObject();
                moduleObj.addProperty("enabled", module.isEnabled());
                moduleObj.addProperty("keybind", module.getKeybind());
                modules.add(module.getName(), moduleObj);
            }
            
            root.add("modules", modules);
            root.addProperty("version", FutureClient.VERSION);
            
            GSON.toJson(root, writer);
            FutureClient.LOGGER.info("Configuration saved");
        } catch (IOException e) {
            FutureClient.LOGGER.error("Failed to save configuration", e);
        }
    }
    
    public void load() {
        if (!configFile.exists()) {
            FutureClient.LOGGER.info("No configuration file found, using defaults");
            return;
        }
        
        try (FileReader reader = new FileReader(configFile)) {
            JsonObject root = GSON.fromJson(reader, JsonObject.class);
            
            if (root.has("modules")) {
                JsonObject modules = root.getAsJsonObject("modules");
                
                for (Module module : FutureClient.getInstance().getModuleManager().getModules()) {
                    if (modules.has(module.getName())) {
                        JsonObject moduleObj = modules.getAsJsonObject(module.getName());
                        
                        if (moduleObj.has("enabled")) {
                            module.setEnabled(moduleObj.get("enabled").getAsBoolean());
                        }
                        
                        if (moduleObj.has("keybind")) {
                            module.setKeybind(moduleObj.get("keybind").getAsInt());
                        }
                    }
                }
            }
            
            FutureClient.LOGGER.info("Configuration loaded");
        } catch (Exception e) {
            FutureClient.LOGGER.error("Failed to load configuration", e);
        }
    }
                              }
