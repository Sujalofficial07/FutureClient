package com.futureclient.impl.modules.render

import com.futureclient.FutureClient
import com.futureclient.api.module.Category
import com.futureclient.api.module.Module
import net.minecraft.client.gui.*
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.awt.Color

class CustomMenuModule : Module("CustomMenu", Category.MISC) {
    init { toggled = true }
    
    @SubscribeEvent
    fun onGuiOpen(event: GuiOpenEvent) {
        if (event.gui is GuiMainMenu) {
            event.gui = FutureClientMainMenu()
        }
    }
}

class FutureClientMainMenu : GuiScreen() {
    override fun initGui() {
        val h = this.height / 4 + 48
        this.buttonList.add(GuiButton(1, this.width / 2 - 100, h, "Singleplayer"))
        this.buttonList.add(GuiButton(2, this.width / 2 - 100, h + 24, "Multiplayer"))
        this.buttonList.add(GuiButton(0, this.width / 2 - 100, h + 48, "Options"))
        this.buttonList.add(GuiButton(4, this.width / 2 - 100, h + 72, "Quit"))
    }

    override fun actionPerformed(button: GuiButton) {
        when (button.id) {
            1 -> mc.displayGuiScreen(GuiSelectWorld(this))
            2 -> mc.displayGuiScreen(GuiMultiplayer(this))
            0 -> mc.displayGuiScreen(GuiOptions(this, mc.gameSettings))
            4 -> mc.shutdown()
        }
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawGradientRect(0, 0, this.width, this.height, Color(10, 10, 10).rgb, Color(40, 10, 40).rgb)
        val title = "${FutureClient.NAME} v${FutureClient.VERSION}"
        val scale = 2.0f
        net.minecraft.client.renderer.GlStateManager.scale(scale, scale, scale)
        drawCenteredString(fontRendererObj, title, (width / 2 / scale).toInt(), 30, Color(0, 150, 255).rgb)
        net.minecraft.client.renderer.GlStateManager.scale(1/scale, 1/scale, 1/scale)
        super.drawScreen(mouseX, mouseY, partialTicks)
    }
}
