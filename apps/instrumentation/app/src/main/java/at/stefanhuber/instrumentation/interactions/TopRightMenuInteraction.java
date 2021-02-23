package at.stefanhuber.instrumentation.interactions;

public class TopRightMenuInteraction extends Interaction {
    @Override
    public void interact() {
        uiDevice.click(calculateRelativeXPosition(0.9), calculateRelativeYPosition(0.1));
    }
}
