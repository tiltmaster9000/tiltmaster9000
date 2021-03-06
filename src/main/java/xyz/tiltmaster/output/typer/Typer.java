package xyz.tiltmaster.output.typer;

import xyz.tiltmaster.output.ActivityNotifier;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * The Typer types a message into the system.
 */
public abstract class Typer {
    Robot robot;
    private final ActivityNotifier activityNotifier;

    /**
     * Creates a new typer.
     *
     * @param activityNotifier The activity notifier that back propagates the typer activity status through the program.
     */
    Typer(ActivityNotifier activityNotifier) {
        this.activityNotifier = activityNotifier;

        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Type the given string.
     *
     * @param string The string to type.
     */
    public void type(final String string) {
        activityNotifier.fire(true);
        this.pressEnter();

        for (char c : string.toCharArray()) {
            final int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException("Key code not found for character '" + c + "'");
            }

            switch (c) {
                case '/':
                    this.pressForwardSlash();
                    break;
                case '?':
                    this.pressQuestionMark();
                    break;
                case '!':
                    this.pressExlamationMark();
                    break;
                case ':':
                    this.pressColon();
                    break;
                case ';':
                    this.pressSemiColon();
                    break;
                case '(':
                    this.pressParenOpen();
                    break;
                case ')':
                    this.pressParenClose();
                    break;
                case '\'':
                    this.pressApostrophe();
                    break;
                case '<':
                    this.pressLessThan();
                    break;
                case '>':
                    this.pressGreaterThan();
                    break;
                case '*':
                    this.pressStar();
                    break;
                case '=':
                    this.pressEquals();
                    break;
                default:
                    this.pressNormalKey(c, keyCode);
            }
        }


        this.pressEnter();

        // Dirty fix for threading
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        activityNotifier.fire(false);
    }

    /**
     * Implement a virtual enter key press.
     */
    private void pressEnter() {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    /**
     * Implement a virtual forward slash key press.
     *
     * @param c       The character that should be pressed.
     * @param keyCode The keyCode of that key that should pressed.
     */
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

    /**
     * Implement a virtual forward slash key press.
     */
    abstract void pressForwardSlash();

    /**
     * Implement a virtual exclamation key press.
     */
    abstract void pressExlamationMark();

    /**
     * Implement a virtual question mark key press.
     */
    abstract void pressQuestionMark();


    /**
     * Implement a virtual colon key press.
     */
    abstract void pressColon();

    /**
     * Implement a virtual semicolon key press.
     */
    abstract void pressSemiColon();

    /**
     * Implement a virtual parentheses open key press.
     */
    abstract void pressParenOpen();

    /**
     * Implement a virtual parentheses close key press.
     */
    abstract void pressParenClose();

    /**
     * Implement a virtual greater than key press.
     */
    abstract void pressGreaterThan();

    /**
     * Implement a virtual less than key press.
     */
    abstract void pressLessThan();

    /**
     * Implement a virtual star key press.
     */
    abstract void pressStar();

    /**
     * Implement a virtual apostrophe key press.
     */
    abstract void pressApostrophe();

    /**
     * Implement a virtual equals key press
     */
    abstract void pressEquals();

    /**
     * Generate a random key press time that should be about realistic to a real typing speed.
     *
     * @return A random key press time that should be about realistic to a real typing speed.
     */
    int generateRandomPressTime() {
        return (int) (Math.random() * 30) + 5;
    }
}
