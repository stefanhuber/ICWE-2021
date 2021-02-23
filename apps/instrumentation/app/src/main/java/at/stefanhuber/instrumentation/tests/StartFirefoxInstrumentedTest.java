package at.stefanhuber.instrumentation.tests;

import android.os.Build;
import android.os.SystemClock;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.stefanhuber.instrumentation.interactions.ClickInteraction;
import at.stefanhuber.instrumentation.interactions.CloseAppInteraction;
import at.stefanhuber.instrumentation.interactions.Interaction;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StartFirefoxInstrumentedTest {

    @Test
    public void fullTest() {
        Interaction close = new CloseAppInteraction();
        close.interact();

        double x = 0.38;
        double y = 0.45;
        if (Build.MODEL.equals("Nexus 5X")) {
            x = 0.3;
            y = 0.5;
        } else if (Build.MODEL.equals("Nexus 5")) {
            x = 0.4;
            y = 0.55;
        }

        Interaction openInteraction = new ClickInteraction(x, y);
        openInteraction.interact();
    }

}
