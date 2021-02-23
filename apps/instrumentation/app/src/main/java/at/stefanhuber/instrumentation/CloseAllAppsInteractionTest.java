package at.stefanhuber.instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import at.stefanhuber.instrumentation.interactions.BackMenuInteraction;
import at.stefanhuber.instrumentation.interactions.CloseAppInteraction;
import at.stefanhuber.instrumentation.interactions.Interaction;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CloseAllAppsInteractionTest {

    @Test
    public void startInteraction() {
        for (int i = 0; i < 2; i++) {
            Interaction close = new CloseAppInteraction();
            close.start();
        }
    }

}
