package org.bookmc.event.api;

import java.util.HashSet;
import java.util.Set;

public class EventRegistry<T> {
    private final Set<Event<T>> events = new HashSet<>();

    public void post(T data) {
        for (Event<T> event : events) {
            if (event.onEvent(data)) {
                break;
            }
        }
    }

    public void register(Event<T> event) {
        events.add(event);
    }
}
