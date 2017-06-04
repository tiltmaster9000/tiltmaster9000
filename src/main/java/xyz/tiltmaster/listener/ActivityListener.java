package xyz.tiltmaster.listener;

import xyz.tiltmaster.util.IListener;


public class ActivityListener implements IListener<Boolean> {
    private boolean isActive = false;

    @Override
    public void activate(Boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }
}
