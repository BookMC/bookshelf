package org.bookmc.event.impl;

import org.bookmc.event.api.Cancel;
import org.bookmc.event.api.Event;
import org.bookmc.event.impl.cancel.Cancellable;
import org.bookmc.event.impl.cancel.ImmutableCancel;

import java.util.HashSet;
import java.util.Set;

public class EventRegistry<T> {
    private final Set<Event<T>> events = new HashSet<>();

    @SuppressWarnings({"UnusedReturnValue", "ConstantConditions"})
    public boolean post(T data, boolean cancellable) {
        Cancel cancel = cancellable ? new Cancellable() : new ImmutableCancel(cancellable);

        for (Event<T> event : events) {
            event.onEvent(data, cancel);
            if (cancel.isCancelled()) {
                return true;
            }

        }
        return false;
    }

    public void register(Event<T> event) {
        events.add(event);
    }
}
