package com.futureclient.manager;

import com.futureclient.FutureClient;
import com.futureclient.api.Module;
import com.futureclient.gui.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class EventManager {

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().thePlayer == null || Minecraft.getMinecraft().theWorld == null) return;
        
        if (event.phase == TickEvent.Phase.START) {
            for (Module m : FutureClient.instance.moduleManager.modules) {
                if (m.isToggled()) m.onUpdate();
            }
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (!Keyboard.getEventKeyState()) return; // Only trigger on press, not release
        
        int key = Keyboard.getEventKey();
        
        // Open Click GUI on Right Shift
        if (key == Keyboard.KEY_RSHIFT) {
            Minecraft.getMinecraft().displayGuiScreen(new ClickGui());
        }
        
        FutureClient.instance.moduleManager.onKey(key);
    }
}
