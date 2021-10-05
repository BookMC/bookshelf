package org.bookmc.abstraction.impl.client;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import org.bookmc.abstraction.api.client.MinecraftGameClient;
import org.bookmc.abstraction.api.client.session.PlayerSession;
import org.bookmc.abstraction.impl.client.session.V1_17_1Session;

public class V1_17_1MinecraftGameClient implements MinecraftGameClient {
    @Override
    public String getVersion() {
        return SharedConstants.getGameVersion().getName();
    }

    @Override
    public PlayerSession getCurrentSession() {
        return new V1_17_1Session(MinecraftClient.getInstance().getSession());
    }
}
