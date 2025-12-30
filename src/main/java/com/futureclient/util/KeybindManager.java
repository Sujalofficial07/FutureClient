package com.futureclient.util;

import com.futureclient.FutureClient;
import com.futureclient.module.Module;
import org.lwjgl.glfw.GLFW;

public class KeybindManager {
    
    public void onTick() {
        for (Module module : FutureClient.getInstance().getModuleManager().getModules()) {
            if (module.getKeybind() != -1) {
                long window = net.minecraft.client.MinecraftClient.getInstance().getWindow().getHandle();
                if (GLFW.glfwGetKey(window, module.getKeybind()) == GLFW.GLFW_PRESS) {
                    module.toggle();
                }
            }
        }
    }
}
