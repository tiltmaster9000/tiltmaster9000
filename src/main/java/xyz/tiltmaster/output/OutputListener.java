package xyz.tiltmaster.output;

import xyz.tiltmaster.output.typer.DETyper;
import xyz.tiltmaster.output.typer.Typer;
import xyz.tiltmaster.output.typer.TyperLocale;
import xyz.tiltmaster.output.typer.USTyper;
import xyz.tiltmaster.util.IListener;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class OutputListener implements IListener<String> {
    private BlockingQueue<String> blockingQueue;
    private Typer typer;

    public OutputListener(final TyperLocale locale) {
        this.blockingQueue = new LinkedBlockingDeque<>();
        this.typer = chooseTyper(locale);
    }

    private Typer chooseTyper(final TyperLocale locale) {
        switch (locale) {
            case US:
                return new USTyper();
            case DE:
                return new DETyper();
            default:
                return new USTyper();
        }
    }

    @Override
    public void activate(final String string) {
        blockingQueue.add(string);
    }

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
