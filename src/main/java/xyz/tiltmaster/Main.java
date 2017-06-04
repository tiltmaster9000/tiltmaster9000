package xyz.tiltmaster;

import xyz.tiltmaster.output.OutputListener;
import xyz.tiltmaster.output.typer.TyperLocale;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final ExecutorService threadPool = Executors.newCachedThreadPool();

        final OutputListener outputListener = new OutputListener(TyperLocale.US);

        threadPool.submit(outputListener::run);
    }
}
