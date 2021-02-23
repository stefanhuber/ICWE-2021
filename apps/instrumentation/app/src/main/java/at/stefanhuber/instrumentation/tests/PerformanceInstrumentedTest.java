package at.stefanhuber.instrumentation.tests;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

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
public class PerformanceInstrumentedTest {

    long interactionTimes[] = new long[] {
        -1,
        2000,
        -1,
        500,
        -1,
        40000,
        500,
        -1,
        12000,
        3500
    };

    public InteractionSequence createInteractionSequence() {
        List<Interaction> interactions = new ArrayList<Interaction>();
        interactions.add(new WaitInteraction(2000));            // 2000
        interactions.add(new OpenDrawerInteraction());                    // 2000
        interactions.add(new WaitInteraction(2000));            // 2000
        interactions.add(new ClickInteraction(0.5, 0.4));  // 500
        interactions.add(new WaitInteraction(3000));            // 3000
        interactions.add(new EnterFormDataInteraction(1000));           // 40000
        interactions.add(new TopRightMenuInteraction());                  // 500
        interactions.add(new WaitInteraction(3000));            // 3000
        interactions.add(new ScrollDownListInteraction());                // 12000
        interactions.add(new RemoveCenterListItemInteraction());          // 3500

        for (int i = 0; i < interactions.size(); i++) {
            interactions.get(i).setExactDuration(interactionTimes[i]);
        }

        return new InteractionSequence(interactions);
    }

    @Test
    public void fullTest() {
        InteractionSequence sequence = createInteractionSequence();
        sequence.start();

        /*
        long duration = sequence.getDuration();
        Interaction wait = new WaitInteraction(75000 - duration);
        wait.start();
        Log.i("PERF_TEST", sequence.getDuration() + "");
        Log.i("PERF_TEST", sequence.getPrintableDurations());
        */

    }

}
