package com.futureclient.gui;

import com.futureclient.FutureClient;
import com.futureclient.api.Category;
import com.futureclient.api.Module;
import com.futureclient.util.RenderUtil;
import net.minecraft.client.gui.GuiScreen;
import java.awt.Color;
import java.io.IOException;

public class ClickGui extends GuiScreen {
    private int panelWidth = 100;
    private int panelHeight = 18;
    private int gap = 110;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        int x = 20;
        int y = 20;

        for (Category cat : Category.values()) {
            // Draw Category Header
            RenderUtil.drawRect(x, y, x + panelWidth, y + panelHeight, new Color(50, 50, 50, 255).getRGB());
            mc.fontRendererObj.drawStringWithShadow(cat.name(), x + 5, y + 5, -1);

            int moduleY = y + panelHeight;
            
            for (Module m : FutureClient.instance.moduleManager.getModulesByCategory(cat)) {
                boolean hovered = isHovered(x, moduleY, panelWidth, panelHeight, mouseX, mouseY);
                int color = m.isToggled() ? new Color(0, 150, 0, 200).getRGB() : new Color(0, 0, 0, 180).getRGB();
                if (hovered) color = new Color(100, 100, 100, 200).getRGB();

                RenderUtil.drawRect(x, moduleY, x + panelWidth, moduleY + panelHeight, color);
                mc.fontRendererObj.drawString(m.getName(), x + 5, moduleY + 5, -1);
                
                moduleY += panelHeight;
            }
            x += gap;
        }
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        int x = 20;
        int y = 20;

        for (Category cat : Category.values()) {
            int moduleY = y + panelHeight;
            
            for (Module m : FutureClient.instance.moduleManager.getModulesByCategory(cat)) {
                if (isHovered(x, moduleY, panelWidth, panelHeight, mouseX, mouseY)) {
                    if (mouseButton == 0) { // Left Click
                        m.toggle();
                        FutureClient.instance.configManager.save(); // Save on change
                    }
                }
                moduleY += panelHeight;
            }
            x += gap;
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    private boolean isHovered(int x, int y, int width, int height, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
    
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
