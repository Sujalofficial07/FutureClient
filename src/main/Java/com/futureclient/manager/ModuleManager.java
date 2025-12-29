package com.futureclient.manager;

import com.futureclient.api.Category;
import com.futureclient.api.Module;
import com.futureclient.modules.hud.*;
import com.futureclient.modules.pvp.*;
import com.futureclient.modules.render.*;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public List<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
        // HUD
        modules.add(new CPS());
        modules.add(new FPS());
        modules.add(new Coordinates());
        modules.add(new Keystrokes());
        
        // PVP
        modules.add(new ToggleSprint());
        modules.add(new Zoom());
        
        // RENDER
        modules.add(new Fullbright());
        
        // Default GUI Bind: RSHIFT
        // Note: ClickGUI is handled separately in EventManager usually, 
        // but for modularity, let's keep it here if we made it a module.
        // For this architecture, ClickGUI is a screen, not a toggle module.
    }

    public List<Module> getModulesByCategory(Category category) {
        List<Module> out = new ArrayList<Module>();
        for (Module m : modules) {
            if (m.getCategory() == category) out.add(m);
        }
        return out;
    }

    public Module getModule(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

    public void onKey(int key) {
        if (key == 0) return;
        for (Module m : modules) {
            if (m.getKey() == key) m.toggle();
        }
    }
}
