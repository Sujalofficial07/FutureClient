package com.futureclient.manager;

import com.futureclient.FutureClient;
import com.futureclient.api.Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;

import java.io.*;

public class ConfigManager {
    private File configFile;
    private Gson gson;

    public ConfigManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.configFile = new File(Minecraft.getMinecraft().mcDataDir, "futureclient_config.json");
    }

    public void save() {
        JsonObject json = new JsonObject();
        for (Module m : FutureClient.instance.moduleManager.modules) {
            JsonObject moduleJson = new JsonObject();
            moduleJson.addProperty("toggled", m.isToggled());
            moduleJson.addProperty("key", m.getKey());
            json.add(m.getName(), moduleJson);
        }

        try (FileWriter writer = new FileWriter(configFile)) {
            gson.toJson(json, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        if (!configFile.exists()) return;

        try (FileReader reader = new FileReader(configFile)) {
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(reader);

            for (Module m : FutureClient.instance.moduleManager.modules) {
                if (json.has(m.getName())) {
                    JsonObject moduleJson = json.getAsJsonObject(m.getName());
                    if (moduleJson.has("toggled")) {
                        m.setToggled(moduleJson.get("toggled").getAsBoolean());
                    }
                    if (moduleJson.has("key")) {
                        m.setKey(moduleJson.get("key").getAsInt());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
