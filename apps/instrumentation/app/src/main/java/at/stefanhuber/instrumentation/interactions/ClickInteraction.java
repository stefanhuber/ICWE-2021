package at.stefanhuber.instrumentation.interactions;

public class ClickInteraction extends Interaction {

    protected double x;
    protected double y;

    public ClickInteraction(double relativeX, double relativeY) {
        super();
        this.x = relativeX;
        this.y = relativeY;
    }

    @Override
    public void interact() {
        uiDevice.click(calculateRelativeXPosition(x), calculateRelativeYPosition(y));
    }
}
