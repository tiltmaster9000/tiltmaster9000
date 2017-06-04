package xyz.tiltmaster.output;

import xyz.tiltmaster.util.IListener;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class OutputListener implements IListener<String> {
    private BlockingQueue<String> blockingQueue;
    private Typer typer;

    public OutputListener() {
        this.blockingQueue = new LinkedBlockingDeque<>();
        this.typer = new Typer();
    }

    @Override
    public void activate(String string) {
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
