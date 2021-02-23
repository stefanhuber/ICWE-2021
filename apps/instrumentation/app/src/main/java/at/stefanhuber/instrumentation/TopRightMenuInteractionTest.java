package at.stefanhuber.instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import at.stefanhuber.instrumentation.interactions.Interaction;
import at.stefanhuber.instrumentation.interactions.TopRightMenuInteraction;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TopRightMenuInteractionTest {

    @Test
    public void startInteraction() {
        Interaction pre = new TopRightMenuInteraction();
        pre.setExactDuration(3000);
        pre.start();
    }

}
