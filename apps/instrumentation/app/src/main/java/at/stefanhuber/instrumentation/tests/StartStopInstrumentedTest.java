package at.stefanhuber.instrumentation.tests;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.Test;
import org.junit.runner.RunWith;

import at.stefanhuber.instrumentation.interactions.ClickInteraction;
import at.stefanhuber.instrumentation.interactions.Interaction;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StartStopInstrumentedTest {

    @Test
    public void fullTest() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

        UiDevice device = UiDevice.getInstance(instrumentation);
        device.pressHome();
        device.pressRecentApps();
        SystemClock.sleep(1000);

        device.swipe((int) (device.getDisplayWidth() * 0.5), (int) (device.getDisplayHeight() * 0.5), (int) (device.getDisplayWidth()), (int) (device.getDisplayHeight() * 0.5), 10);

        SystemClock.sleep(1000);

        device.pressHome();

        Interaction interaction = new ClickInteraction(0.3, 0.5);
        interaction.interact();

    /*
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage("at.stefanhuber.contactappnative");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    */
        SystemClock.sleep(1000);

    }

}
