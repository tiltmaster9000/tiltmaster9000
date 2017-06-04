package xyz.tiltmaster.output.typer;

import xyz.tiltmaster.output.typer.Typer;

import java.awt.event.KeyEvent;

public class USTyper extends Typer {

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
