package org.bookmc.hud;

import org.bookmc.event.impl.EventRegistry;

public class RenderOverlayEvent {
    public static final EventRegistry<RenderOverlayEvent> EVENT = new EventRegistry<>();

    private final int scaledWidth;
    private final int scaledHeight;


    public RenderOverlayEvent(int scaledWidth, int scaledHeight) {
        this.scaledWidth = scaledWidth;
        this.scaledHeight = scaledHeight;
    }

    public int getScaledWidth() {
        return scaledWidth;
    }

    public int getScaledHeight() {
        return scaledHeight;
    }
}
