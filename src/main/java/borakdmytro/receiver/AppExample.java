package borakdmytro.receiver;

import borakdmytro.util.MenuIO;

public class AppExample implements Receiver {
    private String color;

    public AppExample() {
        color = "white";
    }

    public void setColor(String color) {
        this.color = color;
        MenuIO.write("New color = " + color);
    }

    public void PowerOn() {
        MenuIO.write("The light is on. color: " + color);
    }

    public void PowerOff() {
        MenuIO.write("The light is off");
    }

}
