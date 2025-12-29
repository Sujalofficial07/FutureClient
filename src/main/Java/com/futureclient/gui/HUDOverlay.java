package com.futureclient.gui;

import com.futureclient.FutureClient;
import com.futureclient.api.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HUDOverlay {
    
    @SubscribeEvent
    public void onRenderGUI(RenderGameOverlayEvent.Text event) {
        // Draw enabled modules in list (optional, Top Right)
        int y = 2;
        for (Module m : FutureClient.instance.moduleManager.modules) {
            if (m.isToggled() && m.getCategory() != com.futureclient.api.Category.HUD) {
                // We exclude HUD modules from the ArrayList because they draw themselves
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(m.getName(), 
                    event.resolution.getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(m.getName()) - 2, 
                    y, -1);
                y += 10;
            }
        }

        // Draw HUD Modules themselves
        for (Module m : FutureClient.instance.moduleManager.modules) {
            if (m.isToggled()) {
                m.onRender2D();
            }
        }
    }
}
