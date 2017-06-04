package xyz.tiltmaster.output;

import java.awt.*;
import java.awt.event.KeyEvent;


public class Typer {
    private Robot robot;

    public Typer() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void type(String string) {
        this.pressEnter();

        for (char c : string.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException("Key code not found for character '" + c + "'");
            }

            switch (c) {
                case '?':
                    // pressQuestionMark();
                    System.out.println("No question mark implemented at this time");
                case '!':
                    pressExlamationMark();
                default:
                    pressNormalKey(c, keyCode);
            }
        }


        this.pressEnter();
    }

    private void pressEnter() {
        robot.keyPress(KeyEvent.VK_ENTER);
        try {
            Thread.sleep(this.generateRandomPressTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    private void pressNormalKey(char c, int keyCode) {
        if (Character.isUpperCase(c)) {
            robot.keyPress(KeyEvent.VK_SHIFT);
        }
        robot.keyPress(keyCode);
        robot.delay(this.generateRandomPressTime());

        if (Character.isUpperCase(c)) {
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }
        robot.keyRelease(keyCode);
        robot.delay(this.generateRandomPressTime());
    }

    private void pressExlamationMark() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_1);
        try {
            Thread.sleep(this.generateRandomPressTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_1);
    }

    private void pressQuestionMark() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        //robot.keyPress(KeyEvent.);
        try {
            Thread.sleep(this.generateRandomPressTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.keyRelease(KeyEvent.VK_SHIFT);
        //
        // robot.keyRelease(KeyEvent.VK_EXCLAMATION_MARK);
    }

    private int generateRandomPressTime() {
        return 50;
    }
}
