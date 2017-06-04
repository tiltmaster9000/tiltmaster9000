package xyz.tiltmaster.listener;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import xyz.tiltmaster.util.Observer;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by David on 04.06.2017.
 */
public class KeyboardListener extends Observer implements NativeKeyListener {
    private static String message;
    private static Properties properties;

    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + /*NativeKeyEvent.getKeyText*/(e.getKeyCode()));
        message = properties.getProperty(NativeKeyEvent.getKeyText(e.getKeyCode()));
        this.fire(() -> message);
        System.out.println(message);
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public static void main(String[] args) {
        properties = new Properties();

        BufferedInputStream stream = null;
        try {
            stream = new BufferedInputStream(new FileInputStream("../../keymap.properties"));
            properties.load(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new KeyboardListener());
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
    }


}
