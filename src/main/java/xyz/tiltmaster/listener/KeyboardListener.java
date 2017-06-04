package xyz.tiltmaster.listener;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import xyz.tiltmaster.util.Notifier;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class KeyboardListener extends Notifier<String> implements NativeKeyListener {
    private final Properties properties;

    private final ActivityListener activityListener;

    public KeyboardListener() {
        super();

        properties = new Properties();
        this.activityListener = new ActivityListener();

        BufferedInputStream stream;
        try {
            stream = new BufferedInputStream(new FileInputStream("../keymap.properties"));
            properties.load(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        // Set logger level to warning
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
    }

    public ActivityListener getActivityListener() {
        return activityListener;
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + /*NativeKeyEvent.getKeyText*/(e.getKeyCode()));
        String message = properties.getProperty(NativeKeyEvent.getKeyText(e.getKeyCode()));
        if (message != null && !activityListener.isActive()) {
            this.fire(message);
        }
        System.out.println(message);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void listen() {
        GlobalScreen.addNativeKeyListener(this);
    }
}
