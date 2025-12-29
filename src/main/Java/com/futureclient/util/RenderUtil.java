package com.futureclient.util;

import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class RenderUtil {
    
    public static void drawRect(int left, int top, int right, int bottom, int color) {
        Gui.drawRect(left, top, right, bottom, color);
    }

    // Add more advanced OpenGL rendering helpers here if needed (Rounded rects, etc)
}
