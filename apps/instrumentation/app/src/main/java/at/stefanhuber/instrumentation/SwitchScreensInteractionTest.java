package at.stefanhuber.instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import at.stefanhuber.instrumentation.interactions.BackMenuInteraction;
import at.stefanhuber.instrumentation.interactions.Interaction;
import at.stefanhuber.instrumentation.interactions.InteractionSequence;
import at.stefanhuber.instrumentation.interactions.TopRightMenuInteraction;
import at.stefanhuber.instrumentation.interactions.WaitInteraction;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SwitchScreensInteractionTest {

    @Test
    public void startInteraction() {
        List<Interaction> interactions = new ArrayList<>();
        interactions.add(new TopRightMenuInteraction());
        interactions.add(new WaitInteraction(1500));
        interactions.add(new BackMenuInteraction());
        interactions.add(new WaitInteraction(1500));
        Interaction seq = new InteractionSequence(interactions);
        seq.setExactDuration(3300);

        for (int i = 0; i < 5; i++) {
            seq.start();
        }
    }

}
