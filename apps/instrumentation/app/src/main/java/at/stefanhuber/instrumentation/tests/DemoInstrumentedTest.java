package at.stefanhuber.instrumentation.tests;

import android.app.Instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DemoInstrumentedTest {

    @Test
    public void test() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        String name = UiDevice.getInstance(instrumentation).getProductName();
        System.out.println(name);
    }

}
