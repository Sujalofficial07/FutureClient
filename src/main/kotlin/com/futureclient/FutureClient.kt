package com.futureclient

import com.futureclient.api.config.ConfigManager
import com.futureclient.api.module.ModuleManager
import com.futureclient.impl.gui.click.ClickGui
import com.futureclient.impl.modules.render.CustomMenu
import net.minecraft.client.Minecraft
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.lwjgl.input.Keyboard
import java.io.File

@Mod(modid = FutureClient.MODID, version = FutureClient.VERSION, name = FutureClient.NAME)
class FutureClient {

    companion object {
        const val MODID = "futureclient"
        const val NAME = "FutureClient"
        const val VERSION = "1.0"

        @JvmStatic lateinit var instance: FutureClient
        val mc: Minecraft = Minecraft.getMinecraft()
    }

    lateinit var moduleManager: ModuleManager
    lateinit var configManager: ConfigManager
    lateinit var clickGui: ClickGui

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        instance = this
        val directory = File(mc.mcDataDir, "FutureClient")
        if (!directory.exists()) directory.mkdirs()

        moduleManager = ModuleManager()
        configManager = ConfigManager(File(directory, "config.json"))
        clickGui = ClickGui()

        MinecraftForge.EVENT_BUS.register(this)
        MinecraftForge.EVENT_BUS.register(moduleManager)
        
        // Initial load
        configManager.loadConfig()
    }

    @SubscribeEvent
    fun onKeyInput(event: InputEvent.KeyInputEvent) {
        if (Keyboard.getEventKeyState()) {
            val key = Keyboard.getEventKey()
            if (key == Keyboard.KEY_NONE) return
            
            // Handle Module Toggles
            moduleManager.getModules().filter { it.key == key }.forEach { it.toggle() }

            // Open ClickGUI (Default Right Shift)
            if (key == Keyboard.KEY_RSHIFT) {
                mc.displayGuiScreen(clickGui)
            }
        }
    }
    
    // Shut down hook to save config
    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
       // logic that runs every tick if needed globally
    }
}
