package com.futureclient.util;

import java.util.ArrayList;
import java.util.List;

public class CPSTracker {
    private static final List<Long> leftClicks = new ArrayList<>();
    private static final List<Long> rightClicks = new ArrayList<>();
    
    public static void registerLeftClick() {
        leftClicks.add(System.currentTimeMillis());
    }
    
    public static void registerRightClick() {
        rightClicks.add(System.currentTimeMillis());
    }
    
    public static int getLeftCPS() {
        return getCPS(leftClicks);
    }
    
    public static int getRightCPS() {
        return getCPS(rightClicks);
    }
    
    private static int getCPS(List<Long> clicks) {
        long currentTime = System.currentTimeMillis();
        clicks.removeIf(time -> currentTime - time > 1000);
        return clicks.size();
    }
}
