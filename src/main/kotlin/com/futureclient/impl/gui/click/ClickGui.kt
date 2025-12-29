package com.futureclient.impl.gui.click

import com.futureclient.FutureClient
import com.futureclient.api.module.Category
import com.futureclient.util.RenderUtil
import net.minecraft.client.gui.GuiScreen
import java.awt.Color

class ClickGui : GuiScreen() {
    private val panels = ArrayList<Panel>()

    init {
        var x = 20
        for (cat in Category.values()) {
            panels.add(Panel(cat, x, 20))
            x += 110
        }
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        panels.forEach { it.draw(mouseX, mouseY) }
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        panels.forEach { it.mouseClicked(mouseX, mouseY, mouseButton) }
        super.mouseClicked(mouseX, mouseY, mouseButton)
    }
    
    override fun doesGuiPauseGame(): Boolean = false
}

class Panel(val category: Category, var x: Int, var y: Int) {
    private val width = 100
    private val height = 15
    private var open = true
    
    fun draw(mouseX: Int, mouseY: Int) {
        RenderUtil.drawRect(x, y, x + width, y + height, Color(0, 150, 255).rgb)
        FutureClient.mc.fontRendererObj.drawStringWithShadow(category.name, x + 4f, y + 4f, -1)

        if (open) {
            var yOffset = height
            val modules = FutureClient.instance.moduleManager.getModulesByCategory(category)
            
            for (mod in modules) {
                val color = if (mod.toggled) Color(40, 40, 40, 200).rgb else Color(20, 20, 20, 200).rgb
                RenderUtil.drawRect(x, y + yOffset, x + width, y + yOffset + height, color)
                FutureClient.mc.fontRendererObj.drawString(mod.name, x + 4, y + yOffset + 4, if(mod.toggled) -1 else Color.GRAY.rgb)
                yOffset += height
            }
            RenderUtil.drawBorderedRect(x, y, x + width, y + yOffset, 1, Color(0, 100, 200).rgb, 0)
        }
    }

    fun mouseClicked(mouseX: Int, mouseY: Int, button: Int) {
        if (isHovered(mouseX, mouseY, x, y, width, height)) {
            if (button == 1) open = !open
            return
        }
        if (open) {
            var yOffset = height
            val modules = FutureClient.instance.moduleManager.getModulesByCategory(category)
            for (mod in modules) {
                if (isHovered(mouseX, mouseY, x, y + yOffset, width, height)) {
                    if (button == 0) mod.toggle()
                }
                yOffset += height
            }
        }
    }

    private fun isHovered(mx: Int, my: Int, x: Int, y: Int, w: Int, h: Int): Boolean {
        return mx >= x && mx <= x + w && my >= y && my <= y + h
    }
}
