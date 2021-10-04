package org.bookmc.resource.mixin.api;

import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.io.InputStream;

@Mixin(AbstractResourcePack.class)
public interface AbstractResourcePackAccessor {
    @Invoker("readMetadata")
    static <T extends IMetadataSection> T callReadMetadata(IMetadataSerializer serializer, InputStream inputStream, String var2) {
        throw new UnsupportedOperationException();
    }
}
