package xyz.tiltmaster.output.typer;

import java.awt.*;
import java.awt.event.KeyEvent;


public abstract class Typer {
    protected Robot robot;

    public Typer() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void type(final String string) {
        this.pressEnter();

        for (char c : string.toCharArray()) {
            final int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException("Key code not found for character '" + c + "'");
            }

            switch (c) {
                case '?':
                    this.pressQuestionMark();
                case '!':
                    this.pressExlamationMark();
                default:
                    this.pressNormalKey(c, keyCode);
            }
        }


        this.pressEnter();
    }

    private void pressEnter() {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    private void pressNormalKey(final char c, final int keyCode) {
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

    protected abstract void pressExlamationMark();

    protected abstract void pressQuestionMark();

    protected int generateRandomPressTime() {
        return 50;
    }
}
