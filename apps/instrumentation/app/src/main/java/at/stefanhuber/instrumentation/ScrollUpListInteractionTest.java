package at.stefanhuber.instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import at.stefanhuber.instrumentation.interactions.Interaction;
import at.stefanhuber.instrumentation.interactions.ScrollUpListInteraction;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ScrollUpListInteractionTest {

    @Test
    public void startInteraction() {
        Interaction interaction = new ScrollUpListInteraction();
        interaction.setExactDuration(12000);
        interaction.start();
    }

}
