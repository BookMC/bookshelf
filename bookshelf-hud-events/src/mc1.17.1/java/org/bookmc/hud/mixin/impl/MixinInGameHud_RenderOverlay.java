package org.bookmc.hud.mixin.impl;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.bookmc.hud.RenderOverlayEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud_RenderOverlay {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render", at = @At("HEAD"))
    private void bookshelf_hud_events$postHudEvent(MatrixStack matrixStack, float v, CallbackInfo ci) {
        RenderOverlayEvent.EVENT.post(new RenderOverlayEvent(client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight()), false);
    }
}
