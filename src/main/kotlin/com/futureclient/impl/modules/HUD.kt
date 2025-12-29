package com.futureclient.impl.modules.hud

import com.futureclient.FutureClient
import com.futureclient.api.module.BooleanSetting
import com.futureclient.api.module.Category
import com.futureclient.api.module.Module
import com.futureclient.util.RenderUtil
import net.minecraft.client.Minecraft
import org.lwjgl.input.Keyboard
import org.lwjgl.input.Mouse
import java.awt.Color

class HUD : Module("HUD", Category.HUD) {
    
    private val showFPS = BooleanSetting("Show FPS", true)
    private val showCPS = BooleanSetting("Show CPS", true)
    private val showKeystrokes = BooleanSetting("Keystrokes", true)
    
    // CPS Calculation
    private val clicks = mutableListOf<Long>()

    init {
        addSetting(showFPS)
        addSetting(showCPS)
        addSetting(showKeystrokes)
        toggled = true // Enabled by default
    }

    fun renderHUD() {
        val fr = mc.fontRendererObj
        var yOffset = 2

        // Module List
        FutureClient.instance.moduleManager.getModules()
            .filter { it.toggled && it.name != "HUD" && it.name != "ClickGUI" }
            .sortedBy { -fr.getStringWidth(it.name) }
            .forEach {
                fr.drawStringWithShadow(it.name, (mc.displayWidth / 2 - fr.getStringWidth(it.name) - 2).toFloat(), yOffset.toFloat(), -1)
                yOffset += 10
            }

        // FPS
        if (showFPS.value) {
            fr.drawStringWithShadow("FPS: ${Minecraft.getDebugFPS()}", 2f, 2f, -1)
        }

        // CPS
        if (showCPS.value) {
            updateCPS()
            fr.drawStringWithShadow("CPS: ${getCPS()}", 2f, 12f, -1)
        }

        // Keystrokes (Simple WASD)
        if (showKeystrokes.value) {
            renderKeystrokes(2, 25)
        }
    }

    private fun renderKeystrokes(x: Int, y: Int) {
        val size = 20
        val gap = 2
        
        drawKey(x + size + gap, y, size, Keyboard.KEY_W, "W")
        drawKey(x, y + size + gap, size, Keyboard.KEY_A, "A")
        drawKey(x + size + gap, y + size + gap, size, Keyboard.KEY_S, "S")
        drawKey(x + (size + gap) * 2, y + size + gap, size, Keyboard.KEY_D, "D")
    }

    private fun drawKey(x: Int, y: Int, size: Int, key: Int, name: String) {
        val pressed = Keyboard.isKeyDown(key)
        val color = if (pressed) Color(255, 255, 255, 100).rgb else Color(0, 0, 0, 100).rgb
        RenderUtil.drawRect(x, y, x + size, y + size, color)
        mc.fontRendererObj.drawString(name, x + size / 2 - mc.fontRendererObj.getStringWidth(name) / 2, y + size / 2 - 4, -1)
    }

    private fun updateCPS() {
        if (Mouse.isButtonDown(0)) {
            // Basic debounce logic would go here, simplified for display
             // Real CPS requires MouseEvent listener, tracking simple clicks here:
        }
        // Removing old clicks
        val time = System.currentTimeMillis()
        clicks.removeIf { it + 1000 < time }
    }
    
    // Called from an external mouse event
    fun addClick() {
        clicks.add(System.currentTimeMillis())
    }
    
    private fun getCPS(): Int = clicks.size
}
