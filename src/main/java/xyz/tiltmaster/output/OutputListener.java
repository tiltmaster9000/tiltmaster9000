package xyz.tiltmaster.output;

import xyz.tiltmaster.output.typer.DETyper;
import xyz.tiltmaster.output.typer.Typer;
import xyz.tiltmaster.output.typer.USTyper;
import xyz.tiltmaster.util.IListener;

import java.awt.im.InputContext;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Manages
 */
public class OutputListener implements IListener<String> {
    private final BlockingQueue<String> blockingQueue;
    private final ActivityNotifier activityNotifier;
    private final Typer typer;

    /**
     *
     */
    public OutputListener(ActivityNotifier activityNotifier) {
        this.blockingQueue = new LinkedBlockingDeque<>();
        this.activityNotifier = activityNotifier;
        this.typer = chooseTyper();
    }

    /**
     *
     * @return
     */
    private Typer chooseTyper() {
        // Find keyboard type and choose correct typer
        InputContext context = InputContext.getInstance();
        String locale = context.getLocale().toLanguageTag();

        if (locale.contains(Locale.ENGLISH.toLanguageTag())) {
            return new USTyper(activityNotifier);
        } else if (locale.contains(Locale.GERMAN.toLanguageTag())) {
            return new DETyper(activityNotifier);
        } else {
            System.out.println("No typer exists for locale: " + locale + ". US typer was chosen by default.");
            return new USTyper(activityNotifier);
        }
    }

    @Override
    public void activate(String event) {
        blockingQueue.add(event);
    }

    /**
     * Starts the OutputListener.
     */
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            String string = null;
            try {
                string = blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (string != null) {
                typer.type(string);
            }
        }
    }
}