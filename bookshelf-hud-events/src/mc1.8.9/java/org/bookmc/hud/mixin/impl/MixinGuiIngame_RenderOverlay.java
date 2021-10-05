package org.bookmc.hud.mixin.impl;

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.bookmc.hud.RenderOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GuiIngame.class)
public class MixinGuiIngame_RenderOverlay {
    @Inject(method = "renderGameOverlay", locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/ScaledResolution;getScaledHeight()I", ordinal = 0))
    private void bookshelf_hud_events$postHudEvents(float v, CallbackInfo ci, ScaledResolution resolution) {
        RenderOverlayEvent.EVENT.post(new RenderOverlayEvent(resolution.getScaledWidth(), resolution.getScaledHeight()), false);
    }
}
