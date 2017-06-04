package xyz.tiltmaster.output.typer;

import xyz.tiltmaster.output.ActivityNotifier;

import java.awt.event.KeyEvent;

/**
 * A {@link Typer} for a US keyboard.
 */
public class USTyper extends Typer {

    /**
     * Creates a new typer.
     *
     * @param activityNotifier The activity notifier that back propagates the typer activity status through the program.
     */
    public USTyper(ActivityNotifier activityNotifier) {
        super(activityNotifier);
    }

    @Override
    protected void pressForwardSlash() {
        robot.keyPress(KeyEvent.VK_SLASH);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_SLASH);
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
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_SLASH);
        robot.delay(this.generateRandomPressTime());
        robot.keyRelease(KeyEvent.VK_SLASH);
        robot.keyRelease(KeyEvent.VK_EXCLAMATION_MARK);
    }
}
