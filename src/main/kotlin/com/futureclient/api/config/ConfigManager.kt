package com.futureclient.api.config

import com.futureclient.FutureClient
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class ConfigManager(val file: File) {
    private val gson = GsonBuilder().setPrettyPrinting().create()

    fun saveConfig() {
        val json = JsonObject()
        FutureClient.instance.moduleManager.getModules().forEach { mod ->
            val modJson = JsonObject()
            modJson.addProperty("toggled", mod.toggled)
            modJson.addProperty("key", mod.key)
            json.add(mod.name, modJson)
        }
        try {
            FileWriter(file).use { writer -> gson.toJson(json, writer) }
        } catch (e: Exception) { e.printStackTrace() }
    }

    fun loadConfig() {
        if (!file.exists()) return
        try {
            val parser = JsonParser()
            val json = parser.parse(FileReader(file)).asJsonObject
            FutureClient.instance.moduleManager.getModules().forEach { mod ->
                if (json.has(mod.name)) {
                    val modJson = json.getAsJsonObject(mod.name)
                    if (modJson.has("toggled")) {
                        val state = modJson.get("toggled").asBoolean
                        if (mod.toggled != state) mod.toggle()
                    }
                    if (modJson.has("key")) mod.key = modJson.get("key").asInt
                }
            }
        } catch (e: Exception) { e.printStackTrace() }
    }
}
