package at.stefanhuber.instrumentation;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import at.stefanhuber.instrumentation.interactions.BackMenuInteraction;
import at.stefanhuber.instrumentation.interactions.Interaction;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BackMenuInteractionTest {

    @Test
    public void startInteraction() {
        Interaction post = new BackMenuInteraction();
        post.setExactDuration(3000);
        post.start();
    }

}
