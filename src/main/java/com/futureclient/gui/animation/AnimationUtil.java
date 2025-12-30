package com.futureclient.gui.animation;

public class AnimationUtil {
    
    public static double animate(double current, double target, double speed) {
        if (Math.abs(target - current) < 0.001) {
            return target;
        }
        return current + (target - current) * speed;
    }
    
    public static double easeInOut(double t) {
        return t < 0.5 ? 2 * t * t : -1 + (4 - 2 * t) * t;
    }
    
    public static double easeIn(double t) {
        return t * t;
    }
    
    public static double easeOut(double t) {
        return t * (2 - t);
    }
}
