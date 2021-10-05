package org.bookmc.event.impl.cancel;

import org.bookmc.event.api.Cancel;

public class Cancellable implements Cancel {
    private boolean cancelled = false;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void cancel() {
        this.cancelled = true;
    }
}
