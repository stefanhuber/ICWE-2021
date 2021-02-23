package at.stefanhuber.instrumentation.interactions;

import android.app.Instrumentation;
import android.app.UiAutomation;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

public abstract class Interaction {

    protected Instrumentation instrumentation;
    protected Context context;
    protected UiAutomation uiAutomation;
    protected UiDevice uiDevice;
    protected int deviceWidth;
    protected int deviceHeight;
    protected long duration = 0;
    protected long exactDuration = -1;

    public Interaction() {
        this(InstrumentationRegistry.getInstrumentation());
    }

    public Interaction(Instrumentation i) {
        instrumentation = i;
        context = instrumentation.getContext();
        uiAutomation = instrumentation.getUiAutomation();
        uiDevice = UiDevice.getInstance(instrumentation);
        deviceWidth = uiDevice.getDisplayWidth();
        deviceHeight = uiDevice.getDisplayHeight();
    }

    public int calculateRelativeXPosition(double percentage) {
        if (percentage > 1) percentage = 1;
        if (percentage < 0) percentage = 0;
        return (int) (deviceWidth * percentage);
    }

    public int calculateRelativeYPosition(double percentage) {
        if (percentage > 1) percentage = 1;
        if (percentage < 0) percentage = 0;
        return (int) (deviceHeight * percentage);
    }

    public void start() {
        long start = System.currentTimeMillis();
        interact();
        long end = System.currentTimeMillis();
        duration = end - start;

        Log.i("INTERACTION", "interaction duration was: " + (duration));

        if (exactDuration > 0 && duration < exactDuration) {
            SystemClock.sleep(exactDuration - duration);
            end = System.currentTimeMillis();
            duration = end - start;
        } else if (exactDuration > 0 && duration > exactDuration) {
            Log.e("INTERACTION", "interaction took longer than planned: diff = -" + (duration - exactDuration));
        }
    }

    public long getDuration() {
        return duration;
    }

    public void setExactDuration(long duration) {
        exactDuration = duration;
    }

    public abstract void interact();
}
