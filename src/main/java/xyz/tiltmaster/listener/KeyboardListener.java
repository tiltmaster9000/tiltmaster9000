package xyz.tiltmaster.listener;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import xyz.tiltmaster.util.Notifier;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;


public class KeyboardListener extends Notifier<String> implements NativeKeyListener {
    private static final Logger logger = LogManager.getLogger(KeyboardListener.class);

    private final String TILTMASTER_ON = "on";
    private final String TILTMASTER_OFF = "off";

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
            logger.error("Error loading properties file.", e);
        }
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        // Set logger level to warning for jnativehook
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
    }

    public ActivityListener getActivityListener() {
        return activityListener;
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        String message = properties.getProperty(NativeKeyEvent.getKeyText(e.getKeyCode()));
        if (message != null && message.equals(TILTMASTER_ON)) {
            activityListener.setActive(true);
        } else if (message != null && message.equals(TILTMASTER_OFF)) {
            activityListener.setActive(true);
        } else if (message != null && !activityListener.isActive()) {
            logger.info("Firing Message: " + message);
            this.fire(message);
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
    }

    public void listen() {
        GlobalScreen.addNativeKeyListener(this);
    }
}
