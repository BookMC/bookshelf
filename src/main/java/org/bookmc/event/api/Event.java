package org.bookmc.event.api;

@FunctionalInterface
public interface Event<T> {
    /**
     * Called when the event is posted
     * @param event The event to be posted
     */
    void onEvent(T event, Cancel cancel);
}
