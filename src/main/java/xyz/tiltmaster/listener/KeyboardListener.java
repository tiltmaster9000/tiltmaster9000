package xyz.tiltmaster.listener;

import org.simplenativehooks.NativeHookInitializer;
import org.simplenativehooks.NativeKeyHook;
import org.simplenativehooks.events.NativeKeyEvent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import staticResources.BootStrapResources;
import utilities.Function;
import xyz.tiltmaster.util.Notifier;

import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;


public class KeyboardListener extends Notifier<String> {
    private final String TILTMASTER_ON = "on";
    private final String TILTMASTER_OFF = "off";
    private final String TILTMASTER_TOGGLE = "toggle";

    private final JSONObject jsonObject;
    private final ActivityListener activityListener;
    private final NativeKeyHook key = NativeKeyHook.of();

    private boolean toggledUser = false;

    public KeyboardListener() {
        super();
        JSONObject jsonObjectTemp;

        this.activityListener = new ActivityListener();

        JSONParser parser = new JSONParser();
        try {
            jsonObjectTemp = (JSONObject) parser.parse(new FileReader("tiltfile.json"));
        } catch (IOException | ParseException e) {
            jsonObjectTemp = null;
            e.printStackTrace();
        }
        jsonObject = jsonObjectTemp;

        try {
            BootStrapResources.extractResources();
        } catch (IOException e) {
            System.out.println("Cannot extract bootstrap resources.");
            e.printStackTrace();
            System.exit(2);
        }
        NativeHookInitializer.of().start();

        key.setKeyReleased(new Function<NativeKeyEvent, Boolean>() {
            @Override
            public Boolean apply(NativeKeyEvent d) {
                return nativeKeyReleased(d);
            }
        });
    }

    public ActivityListener getActivityListener() {
        return activityListener;
    }

    public void listen() {
        key.startListening();
    }

    private boolean nativeKeyReleased(NativeKeyEvent e) {
        JSONObject obj = (JSONObject) (jsonObject.get(KeyEvent.getKeyText(e.getKey())));
        if (obj == null) {
            return false;
        }

        JSONArray msg = (JSONArray) obj.get("tilts");
        String[] messageArray = jsonArrayToStringArray(msg);
        String message = messageArray[new Random().nextInt(messageArray.length)];

        if (message.equals(TILTMASTER_ON)) {
            toggledUser = false;
            activityListener.setActive(false);
        } else if (message.equals(TILTMASTER_OFF)) {
            toggledUser = false;
            activityListener.setActive(true);
        } else if (message.equals(TILTMASTER_TOGGLE) && (!activityListener.isActive() || toggledUser)) {
            toggledUser = !toggledUser;
            activityListener.setActive(!activityListener.isActive());
        } else if (!activityListener.isActive()) {
            System.out.println("Firing Message: " + message);
            this.fire(message);
        }

        return true;
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
}
