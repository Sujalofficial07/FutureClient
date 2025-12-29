package com.futureclient.util

import net.minecraft.client.gui.Gui

object RenderUtil {
    fun drawRect(left: Int, top: Int, right: Int, bottom: Int, color: Int) {
        Gui.drawRect(left, top, right, bottom, color)
    }

    fun drawBorderedRect(x: Int, y: Int, x2: Int, y2: Int, width: Int, borderColor: Int, insideColor: Int) {
        drawRect(x + width, y + width, x2 - width, y2 - width, insideColor)
        drawRect(x, y, x + width, y2, borderColor)
        drawRect(x2, y, x2 - width, y2, borderColor)
        drawRect(x, y, x2, y + width, borderColor)
        drawRect(x, y2, x2, y2 - width, borderColor)
    }
}
