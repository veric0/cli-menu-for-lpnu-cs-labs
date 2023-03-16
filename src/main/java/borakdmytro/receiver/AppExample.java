package borakdmytro.receiver;

import borakdmytro.util.MenuIO;

public class AppExample implements Receiver {
    private String color;

    public AppExample() {
        color = "default white";
    }

    public void PowerOn() {
        MenuIO.write("The light is on.");
        viewCurrentColor();
    }

    public void PowerOff() {
        MenuIO.write("The light is off");
    }

    public void setColor(String color) {
        this.color = color;
        MenuIO.write("New color = " + color);
    }

    @Override
    public void viewCurrentColor() {
        MenuIO.write("Current color - " + color);
    }

}
