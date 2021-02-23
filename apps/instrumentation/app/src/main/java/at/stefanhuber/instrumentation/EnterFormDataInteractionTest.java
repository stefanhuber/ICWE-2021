package at.stefanhuber.instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.stefanhuber.instrumentation.interactions.BackMenuInteraction;
import at.stefanhuber.instrumentation.interactions.EnterFormDataInteraction;
import at.stefanhuber.instrumentation.interactions.Interaction;
import at.stefanhuber.instrumentation.interactions.TopRightMenuInteraction;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EnterFormDataInteractionTest {

    @Test
    public void startInteraction() {
        Interaction interaction = new EnterFormDataInteraction(1);
        //interaction.setExactDuration(20000); // TODO remove for energy test
        interaction.start();
    }

}
