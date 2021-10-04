package org.bookmc.resource;

import com.google.common.collect.Lists;
import net.minecraft.resource.AbstractFileResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.bookmc.loader.api.mod.ModContainer;
import org.bookmc.loader.api.mod.metadata.ModResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;

public class ModResourcePack extends AbstractFileResourcePack {
    private final ModContainer container;

    public ModResourcePack(ModContainer container) {
        super(new File("ModResourcePack:" + container.getMetadata().getId()));
        this.container = container;
    }

    @Override
    protected InputStream openFile(String s) {
        return container.createModResource(s).getResourceAsStream();
    }

    @Override
    protected boolean containsFile(String s) {
        ModResource resource = container.createModResource(s);
        if (resource == null) {
            return false;
        }

        try (InputStream is = resource.getResourceAsStream()) {
            return is != null;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Collection<Identifier> findResources(ResourceType resourceType, String s, String s1, int i, Predicate<String> predicate) {
        return Collections.emptyList();
    }

    @Override
    public Set<String> getNamespaces(ResourceType resourceType) {
        return Set.of(container.getMetadata().getId());
    }

    @Override
    public void close() {

    }
}
