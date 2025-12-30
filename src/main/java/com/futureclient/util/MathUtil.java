package com.futureclient.util;

public class MathUtil {
    
    public static double interpolate(double current, double target, double speed) {
        return current + (target - current) * speed;
    }
    
    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }
    
    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
    
    public static double ease(double t) {
        return t < 0.5 ? 2 * t * t : -1 + (4 - 2 * t) * t;
    }
}
