package at.stefanhuber.instrumentation.interactions;

import android.os.SystemClock;

public class WaitInteraction extends Interaction {

    protected long milliseconds;

    public WaitInteraction(long milliseconds) {
        super();
        this.milliseconds = milliseconds;
    }

    @Override
    public void interact() {
        SystemClock.sleep(milliseconds);
    }
}
