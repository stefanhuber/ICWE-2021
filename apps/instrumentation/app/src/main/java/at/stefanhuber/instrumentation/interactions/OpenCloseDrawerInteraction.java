package at.stefanhuber.instrumentation.interactions;

import android.os.SystemClock;

public class OpenCloseDrawerInteraction extends OpenDrawerInteraction {
    @Override
    public void interact() {
        super.interact();
        SystemClock.sleep(1000);
        uiDevice.click(calculateRelativeXPosition(0.9), calculateRelativeYPosition(0.8));
    }
}
