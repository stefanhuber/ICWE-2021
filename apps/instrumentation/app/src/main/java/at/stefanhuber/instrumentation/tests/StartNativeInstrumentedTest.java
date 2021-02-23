package at.stefanhuber.instrumentation.tests;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import at.stefanhuber.instrumentation.interactions.ClickInteraction;
import at.stefanhuber.instrumentation.interactions.CloseAppInteraction;
import at.stefanhuber.instrumentation.interactions.Interaction;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StartNativeInstrumentedTest {

    @Test
    public void fullTest() {
        Interaction close = new CloseAppInteraction();
        close.interact();

        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage("at.stefanhuber.contactappnative");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

}
