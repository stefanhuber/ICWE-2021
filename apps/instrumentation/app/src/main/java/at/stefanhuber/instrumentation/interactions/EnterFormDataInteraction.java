package at.stefanhuber.instrumentation.interactions;

import android.os.Build;
import android.os.SystemClock;
import android.view.KeyEvent;

public class EnterFormDataInteraction extends Interaction {

    protected int i = 0;
    protected double formY1 = 0;
    protected double formY2 = 0;
    protected double formY3 = 0;

    public EnterFormDataInteraction(int i) {
        super();
        this.i = i;
        initFormY();
    }

    public void initFormY() {
        //Log.i("MODEL", Build.DEVICE + " " + Build.MODEL);
        //Log.i("MODEL", uiDevice.getDisplayWidth() + " " + uiDevice.getDisplayHeight());
        if (Build.MODEL.equals("Nexus 5X")) {
            formY1 = 0.25;
            formY2 = 0.36;
            formY3 = 0.25;
        } else if (Build.MODEL.equals("Nexus 5")) {
            formY1 = 0.22;
            formY2 = 0.32;
            formY3 = 0.42;
        } else if (Build.MODEL.equals("SM-G960F")) {
            formY1 = 0.18;
            formY2 = 0.27;
            formY3 = 0.38;
        }  else if (Build.MODEL.equals("SM-G901F")) {
            formY1 = 0.20;
            formY2 = 0.36;
            formY3 = 0.20;
        }
    }

    @Override
    public void interact() {
        i++;

        uiDevice.click(calculateRelativeXPosition(0.5), calculateRelativeYPosition(formY1));
        pressStringKeyCodes("Firstname " + i);
        //pressStringKeyCodes("f");
        uiDevice.click(calculateRelativeXPosition(0.5), calculateRelativeYPosition(formY2));
        pressStringKeyCodes("0043 5346 345345 " + i);
        //pressStringKeyCodes("2");
        uiDevice.click(calculateRelativeXPosition(0.5), calculateRelativeYPosition(formY3));
        pressStringKeyCodes("Lastname " + i);
        //pressStringKeyCodes("a");
    }

    public void pressStringKeyCodes(String text) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c >= 48 && c <= 57) // 0~9
                uiDevice.pressKeyCode(c - 41);
            else if (c >= 65 && c <= 90) // A~Z
                uiDevice.pressKeyCode(c - 36, 1);
            else if (c >= 97 && c < 122) // a~z
                uiDevice.pressKeyCode(c - 68);
            else if (c == 32)
                uiDevice.pressKeyCode(KeyEvent.KEYCODE_SPACE);

            SystemClock.sleep(250);
        }
    }
}
