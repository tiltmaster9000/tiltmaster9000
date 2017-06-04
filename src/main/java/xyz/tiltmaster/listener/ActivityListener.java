package xyz.tiltmaster.listener;
import xyz.tiltmaster.util.IListener;


public class ActivityListener implements IListener<Boolean> {
    private boolean isActive = false;

    @Override
    public void activate(Boolean isActive) {
        this.isActive = isActive;
        System.out.println("Setting typing status to: " + !isActive);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
        System.out.println("USER ACTION: Setting typing status to: " + !isActive);
    }
}
