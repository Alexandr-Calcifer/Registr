package ru.spb.iac.appHelper;

import java.awt.*;
import java.awt.event.InputEvent;

import static ru.spb.iac.appHelper.WindowTools.getRect;


public class CryptoPro {

    public void clickButton(int[] position, int xCordinate, int yCordinate) throws WindowNotFoundException, AWTException {
        if (position != null && position.length == 4) {
            int x = position[2] - xCordinate;
            int y = position[3] - yCordinate;
            click(x, y);
        }

    }

    private void click(int x, int y) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void keyPress(int ascii16) throws AWTException {
        Robot bot = new Robot();
        bot.keyPress(ascii16);
        bot.keyRelease(ascii16);
    }

    public static class WindowNotFoundException extends Exception {
        public WindowNotFoundException(String className, String windowName) {
            super(String.format("Window null for className: %s; windowName: %s",
                    className, windowName));
        }
    }

    public void cryptoProCSP(String windowName) {
        int[] position = new int[0];
        try {
            position = getRect(windowName);
        } catch (WindowNotFoundException e) {
            e.printStackTrace();
        }

        try {
            clickButton(position,195, 27);
        } catch (WindowNotFoundException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
