package org.bookmc.resource.impl;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import org.bookmc.loader.api.mod.ModContainer;
import org.bookmc.loader.api.mod.metadata.ModResource;
import org.bookmc.resource.mixin.api.AbstractResourcePackAccessor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Set;

public class ModResourcePack extends AbstractResourcePack {
    private final ModContainer container;

    public ModResourcePack(ModContainer container) {
        super(new File("ModResourcePack:" + container.getMetadata().getId()));
        this.container = container;
    }

    @Override
    protected InputStream getInputStreamByName(String s) {
        return container.createModResource(s).getResourceAsStream();
    }

    @Override
    protected boolean hasResourceName(String s) {
        ModResource resource = container.createModResource(s);
        if (resource == null) {
            return false;
        }

        try (InputStream stream = resource.getResourceAsStream()) {
            return stream != null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public <T extends IMetadataSection> T getPackMetadata(IMetadataSerializer serializer, String s) {
        JsonObject object = createModObject();
        return AbstractResourcePackAccessor.callReadMetadata(serializer, new ByteArrayInputStream(object.toString().getBytes(StandardCharsets.UTF_8)), s);
    }

    private JsonObject createModObject() {
        JsonObject object = new JsonObject();

        JsonObject packObject = new JsonObject();
        packObject.addProperty("pack_format", 1);
        packObject.addProperty("description", Optional.ofNullable(container.getMetadata().getDescription()).orElse(container.getMetadata().getName()));

        object.add("pack", packObject);
        return object;
    }

    @Override
    public Set<String> getResourceDomains() {
        return Set.of(container.getMetadata().getId());
    }

    @Override
    public BufferedImage getPackImage() throws IOException {
        ModResource resource = container.getMetadata().getIcon(container);
        if (resource != null) {
            InputStream stream = resource.getResourceAsStream();
            if (stream != null) {
                return TextureUtil.readBufferedImage(stream);
            }
        }

        try (InputStream stream = this.getClass().getResourceAsStream("/assets/bookshelf-resource-loader/icon/logo_64.png")) {
            if (stream != null) {
                return ImageIO.read(stream);
            }
        }

        return Minecraft.getMinecraft().getResourcePackRepository().rprDefaultResourcePack.getPackImage();
    }
}
