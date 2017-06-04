package xyz.tiltmaster.listener;
import xyz.tiltmaster.util.IListener;

import java.util.concurrent.atomic.AtomicBoolean;


public class ActivityListener implements IListener<Boolean> {
    private AtomicBoolean isActive = new AtomicBoolean(false);

    @Override
    public void activate(Boolean isActive) {
        this.isActive.set(isActive);
        System.out.println("Setting typing status to: " + !isActive);
    }

    public boolean isActive() {
        return isActive.get();
    }

    public void setActive(boolean isActive) {
        this.isActive.set(isActive);
        System.out.println("USER ACTION: Setting typing status to: " + !isActive);
    }
}
