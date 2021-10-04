package org.bookmc.event.api;

public interface Event<T> {
    /**
     * Called when the event is posted
     * @param event The event to be posted
     * @return Whether the event should be cancelled or not
     */
    boolean onEvent(T event);
}
