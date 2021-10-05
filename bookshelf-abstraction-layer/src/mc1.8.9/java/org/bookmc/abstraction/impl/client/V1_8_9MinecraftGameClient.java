package org.bookmc.abstraction.impl.client;

import net.minecraft.client.Minecraft;
import org.bookmc.abstraction.api.client.MinecraftGameClient;
import org.bookmc.abstraction.api.client.session.PlayerSession;
import org.bookmc.abstraction.impl.client.session.V1_8_9Session;

public class V1_8_9MinecraftGameClient implements MinecraftGameClient {
    private PlayerSession session;

    @Override
    public String getVersion() {
        return Minecraft.getMinecraft().getVersion();
    }

    @Override
    public PlayerSession getCurrentSession() {
        if (session == null) {
            session = new V1_8_9Session(Minecraft.getMinecraft().getSession());
        }

        return session;
    }
}
