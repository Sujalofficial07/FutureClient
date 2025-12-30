package com.futureclient.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class HUDRenderer {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    
    /**
     * Draw text on the HUD with optional shadow
     */
    public static void drawHUDString(DrawContext context, String text, int x, int y, int color, boolean shadow) {
        if (shadow) {
            context.drawTextWithShadow(mc.textRenderer, text, x, y, color);
        } else {
            context.drawText(mc.textRenderer, text, x, y, color, false);
        }
    }
    
    /**
     * Draw text centered at the given position
     */
    public static void drawCenteredString(DrawContext context, String text, int x, int y, int color, boolean shadow) {
        int width = getStringWidth(text);
        drawHUDString(context, text, x - width / 2, y, color, shadow);
    }
    
    /**
     * Draw text aligned to the right
     */
    public static void drawRightAlignedString(DrawContext context, String text, int x, int y, int color, boolean shadow) {
        int width = getStringWidth(text);
        drawHUDString(context, text, x - width, y, color, shadow);
    }
    
    /**
     * Get the width of a string in pixels
     */
    public static int getStringWidth(String text) {
        return mc.textRenderer.getWidth(text);
    }
    
    /**
     * Get the height of the font
     */
    public static int getStringHeight() {
        return mc.textRenderer.fontHeight;
    }
    
    /**
     * Draw a rectangle with the specified color
     */
    public static void drawRect(DrawContext context, int x, int y, int width, int height, int color) {
        context.fill(x, y, x + width, y + height, color);
    }
    
    /**
     * Draw a bordered rectangle
     */
    public static void drawBorderedRect(DrawContext context, int x, int y, int width, int height, int borderColor, int fillColor, int borderWidth) {
        // Draw fill
        context.fill(x + borderWidth, y + borderWidth, x + width - borderWidth, y + height - borderWidth, fillColor);
        
        // Draw borders
        context.fill(x, y, x + width, y + borderWidth, borderColor); // Top
        context.fill(x, y + height - borderWidth, x + width, y + height, borderColor); // Bottom
        context.fill(x, y, x + borderWidth, y + height, borderColor); // Left
        context.fill(x + width - borderWidth, y, x + width, y + height, borderColor); // Right
    }
    
    /**
     * Draw a gradient rectangle
     */
    public static void drawGradient(DrawContext context, int x, int y, int width, int height, int startColor, int endColor) {
        context.fillGradient(x, y, x + width, y + height, startColor, endColor);
    }
    
    /**
     * Draw a horizontal line
     */
    public static void drawHorizontalLine(DrawContext context, int x1, int x2, int y, int color) {
        if (x2 < x1) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        context.fill(x1, y, x2 + 1, y + 1, color);
    }
    
    /**
     * Draw a vertical line
     */
    public static void drawVerticalLine(DrawContext context, int x, int y1, int y2, int color) {
        if (y2 < y1) {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }
        context.fill(x, y1, x + 1, y2 + 1, color);
    }
    
    /**
     * Scale text rendering
     */
    public static void drawScaledString(DrawContext context, String text, int x, int y, int color, float scale, boolean shadow) {
        MatrixStack matrices = context.getMatrices();
        matrices.push();
        matrices.scale(scale, scale, 1.0f);
        
        float scaledX = x / scale;
        float scaledY = y / scale;
        
        if (shadow) {
            context.drawTextWithShadow(mc.textRenderer, text, (int) scaledX, (int) scaledY, color);
        } else {
            context.drawText(mc.textRenderer, text, (int) scaledX, (int) scaledY, color, false);
        }
        
        matrices.pop();
    }
    
    /**
     * Get scaled string width
     */
    public static int getScaledStringWidth(String text, float scale) {
        return (int) (getStringWidth(text) * scale);
    }
    
    /**
     * Draw a progress bar
     */
    public static void drawProgressBar(DrawContext context, int x, int y, int width, int height, float progress, int bgColor, int fillColor, int borderColor) {
        // Clamp progress between 0 and 1
        progress = Math.max(0.0f, Math.min(1.0f, progress));
        
        // Draw background
        drawRect(context, x, y, width, height, bgColor);
        
        // Draw progress fill
        int fillWidth = (int) (width * progress);
        if (fillWidth > 0) {
            drawRect(context, x, y, fillWidth, height, fillColor);
        }
        
        // Draw border
        drawBorderedRect(context, x - 1, y - 1, width + 2, height + 2, borderColor, 0x00000000, 1);
    }
    
    /**
     * Draw text with a background box
     */
    public static void drawStringWithBackground(DrawContext context, String text, int x, int y, int textColor, int bgColor, int padding) {
        int width = getStringWidth(text);
        int height = getStringHeight();
        
        // Draw background
        drawRect(context, x - padding, y - padding, width + padding * 2, height + padding * 2, bgColor);
        
        // Draw text
        drawHUDString(context, text, x, y, textColor, true);
    }
    
    /**
     * Check if mouse is over a rectangular area
     */
    public static boolean isMouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
    
    /**
     * Convert RGB to integer color
     */
    public static int rgb(int r, int g, int b) {
        return (255 << 24) | (r << 16) | (g << 8) | b;
    }
    
    /**
     * Convert RGBA to integer color
     */
    public static int rgba(int r, int g, int b, int a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
    
    /**
     * Extract alpha component from color
     */
    public static int getAlpha(int color) {
        return (color >> 24) & 0xFF;
    }
    
    /**
     * Extract red component from color
     */
    public static int getRed(int color) {
        return (color >> 16) & 0xFF;
    }
    
    /**
     * Extract green component from color
     */
    public static int getGreen(int color) {
        return (color >> 8) & 0xFF;
    }
    
    /**
     * Extract blue component from color
     */
    public static int getBlue(int color) {
        return color & 0xFF;
    }
    
    /**
     * Interpolate between two colors
     */
    public static int interpolateColor(int color1, int color2, float ratio) {
        ratio = Math.max(0.0f, Math.min(1.0f, ratio));
        
        int a1 = getAlpha(color1);
        int r1 = getRed(color1);
        int g1 = getGreen(color1);
        int b1 = getBlue(color1);
        
        int a2 = getAlpha(color2);
        int r2 = getRed(color2);
        int g2 = getGreen(color2);
        int b2 = getBlue(color2);
        
        int a = (int) (a1 + (a2 - a1) * ratio);
        int r = (int) (r1 + (r2 - r1) * ratio);
        int g = (int) (g1 + (g2 - g1) * ratio);
        int b = (int) (b1 + (b2 - b1) * ratio);
        
        return rgba(r, g, b, a);
    }
}
