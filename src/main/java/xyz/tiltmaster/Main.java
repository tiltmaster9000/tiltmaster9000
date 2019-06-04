package xyz.tiltmaster;

import xyz.tiltmaster.listener.KeyboardListener;
import xyz.tiltmaster.output.ActivityNotifier;
import xyz.tiltmaster.output.OutputListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The main class of TiltMaster9000.
 */
public final class Main {
    private final ExecutorService threadPool;
    private final OutputListener outputListener;
    private final KeyboardListener keyboardListener;
    private final ActivityNotifier activityNotifier;


    /**
     * Constructor for main class.
     */
    private Main() {

        this.threadPool = Executors.newCachedThreadPool();
        this.activityNotifier = new ActivityNotifier();
        this.outputListener = new OutputListener(activityNotifier);
        this.keyboardListener = new KeyboardListener();

        activityNotifier.subscribe(keyboardListener.getActivityListener());
        keyboardListener.subscribe(this.outputListener);

        threadPool.submit(outputListener::run);
        threadPool.submit(keyboardListener::listen);
    }

    /**
     * Starts TiltMaster9000.
     *
     * @param args The args for main.
     */
    public static void main(String[] args) {
        new Main();
    }
}
