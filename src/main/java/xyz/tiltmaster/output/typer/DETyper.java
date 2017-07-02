package xyz.tiltmaster.output.typer;

import xyz.tiltmaster.output.ActivityNotifier;

import java.awt.event.KeyEvent;

/**
 * A {@link Typer} for a German keyboard.
 */
public class DETyper extends Typer {

    /**
     * Creates a new typer.
     *
     * @param activityNotifier The activity notifier that back propagates the typer activity status through the program.
     */
    public DETyper(ActivityNotifier activityNotifier) {
        super(activityNotifier);
    }

    @Override
    protected void pressForwardSlash() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_7);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_7);
    }

    @SuppressWarnings("Duplicates")
    @Override
    protected void pressExlamationMark() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_1);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_1);
    }

    @Override
    protected void pressQuestionMark() {
        //TODO NOT WORKING
        /*
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.getExtendedKeyCodeForChar('ß'));
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.getExtendedKeyCodeForChar('ß'));
        */
    }

    @Override
    void pressColon() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_PERIOD);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_PERIOD);
    }

    @Override
    void pressSemiColon() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_COMMA);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_COMMA);
    }

    @Override
    void pressParenOpen() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_8);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_8);
    }

    @Override
    void pressParenClose() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_9);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_9);
    }

    @Override
    void pressGreaterThan() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_LESS);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_LESS);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }

    @Override
    void pressLessThan() {
        robot.keyPress(KeyEvent.VK_LESS);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_LESS);
    }

    @Override
    void pressStar() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_PLUS);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_PLUS);
    }

    @Override
    void pressApostrophe() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_NUMBER_SIGN);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_NUMBER_SIGN);
    }

    @Override
    void pressEquals() {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_0);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_0);
    }
}
