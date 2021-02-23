package at.stefanhuber.instrumentation.interactions;

import android.os.Build;
import android.os.SystemClock;

public class CloseAppInteraction extends Interaction {

    @Override
    public void interact() {
        try {
            uiDevice.pressHome();
            uiDevice.pressRecentApps();
            SystemClock.sleep(1000);

            if (Build.MODEL.equals("Nexus 5X") || Build.MODEL.equals("Nexus 5") || Build.MODEL.equals("SM-G901F")) {
                uiDevice.swipe(calculateRelativeXPosition(0.5), calculateRelativeYPosition(0.5), calculateRelativeXPosition(1), calculateRelativeYPosition(0.5), 10);
                SystemClock.sleep(1000);
            } else {
                uiDevice.click(calculateRelativeXPosition(0.5), calculateRelativeYPosition(0.8));
                SystemClock.sleep(1000);
            }
        } catch (Exception e) {
        }
    }
}
