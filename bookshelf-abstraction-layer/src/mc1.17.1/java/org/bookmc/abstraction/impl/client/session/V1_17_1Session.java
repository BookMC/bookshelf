package org.bookmc.abstraction.impl.client.session;

import com.mojang.util.UUIDTypeAdapter;
import net.minecraft.client.util.Session;
import org.bookmc.abstraction.api.client.session.PlayerSession;

import java.util.UUID;

public class V1_17_1Session implements PlayerSession {
    private final Session session;

    public V1_17_1Session(Session session) {
        this.session = session;
    }

    @Override
    public String getUsername() {
        return session.getUsername();
    }

    @Override
    public UUID getUUID() {
        return UUIDTypeAdapter.fromString(session.getUuid());
    }

    @Override
    public String getAccessToken() {
        return session.getAccessToken();
    }
}
