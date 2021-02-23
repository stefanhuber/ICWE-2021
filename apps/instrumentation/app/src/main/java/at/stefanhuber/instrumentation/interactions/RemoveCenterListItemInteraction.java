package at.stefanhuber.instrumentation.interactions;

import android.os.SystemClock;

public class RemoveCenterListItemInteraction extends Interaction {
    @Override
    public void interact() {
        uiDevice.swipe(calculateRelativeXPosition(0.75), calculateRelativeYPosition(0.5), calculateRelativeXPosition(0.1), calculateRelativeYPosition(0.5), 20);
        SystemClock.sleep(3000);
    }
}
