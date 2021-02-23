package at.stefanhuber.instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.stefanhuber.instrumentation.interactions.Interaction;
import at.stefanhuber.instrumentation.interactions.OpenCloseDrawerInteraction;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class OpenCloseDrawerInteractionTest {

    @Test
    public void startInteraction() {
        Interaction interaction = new OpenCloseDrawerInteraction();
        interaction.setExactDuration(3000);

        for (int i = 0; i < 5; i++) {
            interaction.start();
        }
    }

}
