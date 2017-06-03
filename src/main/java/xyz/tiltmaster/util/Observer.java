package xyz.tiltmaster.util;

import java.util.ArrayList;
import java.util.List;

public abstract class Observer {
    private List<IListener> listeners = new ArrayList<>();

    public void subscribe(IListener listener) {
        this.listeners.add(listener);
    }

    public boolean unsubscripe(IListener listener) {
        return this.listeners.remove(listener);
    }

    public void fire(IEvent event) {
        listeners.forEach(listener -> listener.activate(event));
    }
}
