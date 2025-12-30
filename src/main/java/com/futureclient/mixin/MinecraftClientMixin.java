package com.futureclient.mixin;

import com.futureclient.util.CPSTracker;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    
    @Inject(method = "doAttack", at = @At("HEAD"))
    private void onLeftClick(CallbackInfo ci) {
        CPSTracker.registerLeftClick();
    }
    
    @Inject(method = "doItemUse", at = @At("HEAD"))
    private void onRightClick(CallbackInfo ci) {
        CPSTracker.registerRightClick();
    }
}
