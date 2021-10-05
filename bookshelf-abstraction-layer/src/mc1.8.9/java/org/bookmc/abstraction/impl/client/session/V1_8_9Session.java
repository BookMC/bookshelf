package org.bookmc.abstraction.impl.client.session;

import com.mojang.util.UUIDTypeAdapter;
import net.minecraft.util.Session;
import org.bookmc.abstraction.api.client.session.PlayerSession;

import java.util.UUID;

public record V1_8_9Session(Session session) implements PlayerSession {
    @Override
    public String getUsername() {
        return session.getUsername();
    }

    @Override
    public UUID getUUID() {
        return UUIDTypeAdapter.fromString(this.session.getPlayerID());
    }

    @Override
    public String getAccessToken() {
        return session.getToken();
    }
}
