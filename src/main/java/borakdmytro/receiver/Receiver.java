package borakdmytro.receiver;

/**
 * Receiver - your app. There will be business logic.
 */
public interface Receiver {
    void PowerOn();

    void PowerOff();

    void setColor(String color);

    void viewCurrentColor();

}
