package org.bookmc.resource.mixin.impl;

import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.resources.ResourcePackListEntry;
import org.bookmc.loader.api.loader.BookLoaderBase;
import org.bookmc.loader.api.mod.ModContainer;
import org.bookmc.resource.impl.ModResourcePackListEntry;
import org.bookmc.resource.impl.ModResourcePackManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(GuiScreenResourcePacks.class)
public class MixinGuiScreenResourcePacks_AddResourceEntry {
    @Shadow
    private List<ResourcePackListEntry> selectedResourcePacks;

    @Inject(method = "initGui", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 4, shift = At.Shift.AFTER))
    private void resourceLoader$addResourceEntry(CallbackInfo ci) {
        selectedResourcePacks.addAll(
            ModResourcePackManager.getResourcePackMap()
                .keySet()
                .stream()
                .map(id -> new ModResourcePackListEntry(getContainer(id), ((GuiScreenResourcePacks) (Object) this)))
                .collect(Collectors.toSet())
        );
    }

    @Unique
    private ModContainer getContainer(String id) {
        return BookLoaderBase.INSTANCE.getContainers().get(id);
    }
}
