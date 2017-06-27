package xyz.tiltmaster.listener;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import xyz.tiltmaster.util.Notifier;

import java.io.BufferedInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;


public class KeyboardListener extends Notifier<String> implements NativeKeyListener {
    private final String TILTMASTER_ON = "on";
    private final String TILTMASTER_OFF = "off";

    private final JSONObject jsonObject;
    private final ActivityListener activityListener;

    public KeyboardListener() {
        super();
        JSONObject jsonObjectTemp;

        this.activityListener = new ActivityListener();

        BufferedInputStream stream;
        JSONParser parser = new JSONParser();
        try {
            jsonObjectTemp = (JSONObject) parser.parse(new FileReader("../tiltfile_en.json"));

        } catch (IOException | ParseException e) {
            jsonObjectTemp = null;
            e.printStackTrace();
        }
        jsonObject = jsonObjectTemp;
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
        JSONArray msg = (JSONArray) jsonObject.get(NativeKeyEvent.getKeyText(e.getKeyCode()));
        String[] messageArray = jsonArrayToStringArray(msg);
        String message;
        if  (messageArray != null) {
            message = messageArray[new Random().nextInt(messageArray.length)];
        } else {
            message = null;
        }
        if (message != null && message.equals(TILTMASTER_ON)) {
            activityListener.setActive(false);
        } else if (message != null && message.equals(TILTMASTER_OFF)) {
            activityListener.setActive(true);
        } else if (message != null && !activityListener.isActive()) {
            System.out.println("Firing Message: " + message);
            this.fire(message);
        }
    }

    private String[] jsonArrayToStringArray(JSONArray jsonArray) {
        if (jsonArray != null) {
            String[] stringArray = new String[jsonArray.size()];
            Iterator iterator = jsonArray.iterator();
            for (int i = 0; i < jsonArray.size(); i++) {
                if (iterator.hasNext()) {
                    stringArray[i] = (String) iterator.next();
                }
            }
            return stringArray;
        } else {
            return null;
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
