package com.futureclient.module.modules.combat;

import com.futureclient.module.Module;
import com.futureclient.config.Setting;

/**
 * Old Animations - 1.8.9 style swing and hand animations
 */
public class OldAnimations extends Module {
    private Setting<Boolean> oldSwing = new Setting<>("Old Swing", true);
    private Setting<Boolean> oldBlock = new Setting<>("Old Block", true);
    private Setting<Boolean> oldRod = new Setting<>("Old Rod", true);
    
    public OldAnimations() {
        super("Old Animations", "1.8.9 style animations", Category.COMBAT);
        addSetting(oldSwing);
        addSetting(oldBlock);
        addSetting(oldRod);
    }
    
    public boolean shouldUseOldSwing() {
        return isEnabled() && oldSwing.getValue();
    }
    
    public boolean shouldUseOldBlock() {
        return isEnabled() && oldBlock.getValue();
    }
    
    public boolean shouldUseOldRod() {
        return isEnabled() && oldRod.getValue();
    }
}

/**
 * Hit Feedback - Visual feedback for successful hits (client-side only)
 */
public class HitFeedback extends Module {
    private Setting<Boolean> redFlash = new Setting<>("Red Flash", true);
    private Setting<Boolean> particles = new Setting<>("Particles", true);
    private Setting<Integer> particleAmount = new Setting<>("Particle Amount", 10, 1, 50);
    
    private long lastHitTime = 0;
    private static final long HIT_COOLDOWN = 500; // ms
    
    public HitFeedback() {
        super("Hit Feedback", "Visual hit confirmation", Category.COMBAT);
        addSetting(redFlash);
        addSetting(particles);
        addSetting(particleAmount);
        setEnabled(true);
    }
    
    public void onEntityHit() {
        if (!isEnabled()) return;
        
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastHitTime < HIT_COOLDOWN) return;
        
        lastHitTime = currentTime;
        
        if (redFlash.getValue()) {
            // Trigger subtle red flash effect (implemented in mixin)
        }
    }
    
    public boolean shouldSpawnParticles() {
        return isEnabled() && particles.getValue();
    }
    
    public int getParticleAmount() {
        return particleAmount.getValue();
    }
}
