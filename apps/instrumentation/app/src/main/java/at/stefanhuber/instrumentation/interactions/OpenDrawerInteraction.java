package at.stefanhuber.instrumentation.interactions;

import android.os.SystemClock;

public class OpenDrawerInteraction extends Interaction {
    @Override
    public void interact() {
        uiDevice.swipe(0, calculateRelativeYPosition(0.1), calculateRelativeXPosition(0.5), calculateRelativeYPosition(0.1), 20);
    }
}
