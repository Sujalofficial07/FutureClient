package com.futureclient.gui;

import com.futureclient.FutureClient;
import com.futureclient.module.Category;
import com.futureclient.module.Module;
import com.futureclient.gui.animation.AnimationUtil;
import com.futureclient.render.RenderUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClientMenuScreen extends Screen {
    private Category selectedCategory = Category.HUD;
    private double animation = 0;
    private static final int SIDEBAR_WIDTH = 120;
    private static final int PADDING = 10;
    
    public ClientMenuScreen() {
        super(Text.literal("FutureClient"));
    }
    
    @Override
    protected void init() {
        super.init();
        animation = 0;
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Smooth animation
        animation = AnimationUtil.animate(animation, 1.0, 0.15);
        
        // Background
        renderBackground(context, mouseX, mouseY, delta);
        
        // Sidebar
        renderSidebar(context, mouseX, mouseY);
        
        // Module list
        renderModuleList(context, mouseX, mouseY);
        
        // Title
        String title = "FutureClient v" + FutureClient.VERSION;
        int titleWidth = textRenderer.getWidth(title);
        context.drawTextWithShadow(textRenderer, title, (width - titleWidth) / 2, 20, 0xFFFFFF);
        
        super.render(context, mouseX, mouseY, delta);
    }
    
    private void renderSidebar(DrawContext context, int mouseX, int mouseY) {
        int sidebarX = (int) (20 * animation);
        int sidebarY = 50;
        
        // Sidebar background
        RenderUtil.drawRoundedRect(context, sidebarX, sidebarY, SIDEBAR_WIDTH, height - 100, 5, 0xD0000000);
        
        int yOffset = 0;
        for (Category category : Category.values()) {
            int buttonY = sidebarY + PADDING + yOffset;
            boolean hovered = mouseX >= sidebarX + PADDING && mouseX <= sidebarX + SIDEBAR_WIDTH - PADDING &&
                            mouseY >= buttonY && mouseY <= buttonY + 25;
            boolean selected = category == selectedCategory;
            
            int color = selected ? 0xFF3498db : (hovered ? 0xFF555555 : 0xFF333333);
            RenderUtil.drawRoundedRect(context, sidebarX + PADDING, buttonY, SIDEBAR_WIDTH - PADDING * 2, 25, 3, color);
            
            int textColor = selected ? 0xFFFFFF : 0xAAAAAA;
            context.drawText(textRenderer, category.getDisplayName(), 
                           sidebarX + PADDING + 10, buttonY + 8, textColor, false);
            
            yOffset += 30;
        }
    }
    
    private void renderModuleList(DrawContext context, int mouseX, int mouseY) {
        int listX = (int) (SIDEBAR_WIDTH + 40 + (width - SIDEBAR_WIDTH - 60) * (1 - animation));
        int listY = 50;
        int listWidth = width - SIDEBAR_WIDTH - 80;
        
        // Module list background
        RenderUtil.drawRoundedRect(context, listX, listY, listWidth, height - 100, 5, 0xD0000000);
        
        List<Module> modules = FutureClient.getInstance().getModuleManager().getModulesByCategory(selectedCategory);
        
        int yOffset = 0;
        for (Module module : modules) {
            int moduleY = listY + PADDING + yOffset;
            
            if (moduleY + 40 > listY + height - 100) break;
            
            boolean hovered = mouseX >= listX + PADDING && mouseX <= listX + listWidth - PADDING &&
                            mouseY >= moduleY && mouseY <= moduleY + 35;
            
            int bgColor = module.isEnabled() ? 0xFF2ecc71 : (hovered ? 0xFF555555 : 0xFF2c3e50);
            RenderUtil.drawRoundedRect(context, listX + PADDING, moduleY, listWidth - PADDING * 2, 35, 3, bgColor);
            
            // Module name
            context.drawText(textRenderer, module.getName(), 
                           listX + PADDING + 10, moduleY + 8, 0xFFFFFF, false);
            
            // Module description
            context.drawText(textRenderer, module.getDescription(), 
                           listX + PADDING + 10, moduleY + 20, 0xAAAAAA, false);
            
            yOffset += 40;
        }
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int sidebarX = (int) (20 * animation);
        int sidebarY = 50;
        
        // Check sidebar clicks
        int yOffset = 0;
        for (Category category : Category.values()) {
            int buttonY = sidebarY + PADDING + yOffset;
            if (mouseX >= sidebarX + PADDING && mouseX <= sidebarX + SIDEBAR_WIDTH - PADDING &&
                mouseY >= buttonY && mouseY <= buttonY + 25) {
                selectedCategory = category;
                return true;
            }
            yOffset += 30;
        }
        
        // Check module clicks
        int listX = SIDEBAR_WIDTH + 40;
        int listY = 50;
        int listWidth = width - SIDEBAR_WIDTH - 80;
        
        List<Module> modules = FutureClient.getInstance().getModuleManager().getModulesByCategory(selectedCategory);
        yOffset = 0;
        
        for (Module module : modules) {
            int moduleY = listY + PADDING + yOffset;
            if (mouseX >= listX + PADDING && mouseX <= listX + listWidth - PADDING &&
                mouseY >= moduleY && mouseY <= moduleY + 35) {
                module.toggle();
                return true;
            }
            yOffset += 40;
        }
        
        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    @Override
    public boolean shouldPause() {
        return false;
    }
}
