package org.bookmc.abstraction.api.client.session;

import java.util.UUID;

public interface PlayerSession {
    String getUsername();

    UUID getUUID();

    String getAccessToken();
}
