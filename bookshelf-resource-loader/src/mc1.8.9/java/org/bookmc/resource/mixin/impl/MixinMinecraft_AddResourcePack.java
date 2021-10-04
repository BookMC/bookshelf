package org.bookmc.resource.mixin.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import org.apache.logging.log4j.Logger;
import org.bookmc.loader.api.loader.BookLoaderBase;
import org.bookmc.loader.impl.loader.version.ModSemverVersion;
import org.bookmc.resource.impl.ModResourcePack;
import org.bookmc.resource.impl.ModResourcePackManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(Minecraft.class)
public class MixinMinecraft_AddResourcePack {
    @Shadow
    @Final
    private List<IResourcePack> defaultResourcePacks;

    @Shadow
    @Final
    private static Logger logger;

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", shift = At.Shift.AFTER))
    private void resourceLoader$addResourcePack(CallbackInfo ci) {
        int compareTo = BookLoaderBase.INSTANCE
            .getContainers()
            .get("book-loader")
            .getMetadata()
            .getVersion()
            .compareTo(new ModSemverVersion("1.0.3"));
        System.out.println("Compare: " + compareTo);
        if (compareTo < 0) {
            throw new IllegalStateException("bookshelf-resource-loader requires 1.0.3 or newer!");
        }

        logger.info("Registering mod resource packs");
        defaultResourcePacks.addAll(
            BookLoaderBase.INSTANCE.getContainers()
                .values()
                .stream()
                .map(c -> {
                    ModResourcePack res = new ModResourcePack(c);
                    ModResourcePackManager.getResourcePackMap().put(c.getMetadata().getId(), res);
                    return res;
                })
                .collect(Collectors.toSet())
        );
    }
}
