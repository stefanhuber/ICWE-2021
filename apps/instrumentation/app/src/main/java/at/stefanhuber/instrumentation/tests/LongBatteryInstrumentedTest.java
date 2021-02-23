package at.stefanhuber.instrumentation.tests;

import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import at.stefanhuber.instrumentation.interactions.ClickInteraction;
import at.stefanhuber.instrumentation.interactions.EnterFormDataInteraction;
import at.stefanhuber.instrumentation.interactions.Interaction;
import at.stefanhuber.instrumentation.interactions.InteractionSequence;
import at.stefanhuber.instrumentation.interactions.OpenDrawerInteraction;
import at.stefanhuber.instrumentation.interactions.RemoveCenterListItemInteraction;
import at.stefanhuber.instrumentation.interactions.ScrollDownListInteraction;
import at.stefanhuber.instrumentation.interactions.TopRightMenuInteraction;
import at.stefanhuber.instrumentation.interactions.WaitInteraction;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LongBatteryInstrumentedTest {

    public InteractionSequence createInteractionSequence() {
        List<Interaction> interactions = new ArrayList<Interaction>();
        interactions.add(new OpenDrawerInteraction());
        interactions.add(new WaitInteraction(2000));
        interactions.add(new ClickInteraction(0.5, 0.4));
        interactions.add(new WaitInteraction(4000));
        interactions.add(new EnterFormDataInteraction(1000));
        interactions.add(new WaitInteraction(2000));
        interactions.add(new TopRightMenuInteraction());
        interactions.add(new WaitInteraction(2000));
        interactions.add(new ScrollDownListInteraction());
        interactions.add(new RemoveCenterListItemInteraction());
        interactions.add(new WaitInteraction(2000));
        return new InteractionSequence(interactions);
    }

    @Test
    public void fullTest() {
        InteractionSequence sequence = createInteractionSequence();

        for (int i = 1; i < 10; i++) {
            sequence.start();
            sequence.updateSequenceItem(4, new EnterFormDataInteraction(1000 + i));
        }
    }
}
