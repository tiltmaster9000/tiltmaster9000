package xyz.tiltmaster;

import xyz.tiltmaster.listener.KeyboardListener;
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


    /**
     * Constructor for main class.
     */
    private Main() {
        this.threadPool = Executors.newCachedThreadPool();
        this.outputListener = new OutputListener();
        this.keyboardListener = new KeyboardListener();

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
