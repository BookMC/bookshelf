package org.bookmc.event.impl.cancel;

import org.bookmc.event.api.Cancel;

public class ImmutableCancel implements Cancel {
    private final boolean originalState;

    public ImmutableCancel(boolean originalState) {
        this.originalState = originalState;
    }

    @Override
    public boolean isCancelled() {
        return originalState;
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("This event cannot be cancelled!");
    }
}
