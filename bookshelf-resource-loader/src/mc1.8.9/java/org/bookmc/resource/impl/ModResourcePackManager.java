package org.bookmc.resource.impl;

import net.minecraft.client.resources.IResourcePack;

import java.util.HashMap;
import java.util.Map;

public class ModResourcePackManager {
    private static final Map<String, IResourcePack> resourcePackMap = new HashMap<>();

    public static Map<String, IResourcePack> getResourcePackMap() {
        return resourcePackMap;
    }
}
