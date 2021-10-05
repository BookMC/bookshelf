package org.bookmc.abstraction.api.client;

import org.bookmc.abstraction.api.client.session.PlayerSession;
import org.bookmc.abstraction.registry.BindingRegistry;

public interface MinecraftGameClient {
    String getVersion();

    PlayerSession getCurrentSession();

    static MinecraftGameClient getInstance() {
        return BindingRegistry.getInstance(MinecraftGameClient.class);
    }
}
