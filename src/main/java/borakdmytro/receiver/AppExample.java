package borakdmytro.receiver;

public class AppExample implements Receiver {
    private String color;

    public AppExample() {
        color = "white";
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void PowerOn() {
        System.out.println("The light is on. color: " + color);
    }

    public void PowerOff() {
        System.out.println("The light is off");
    }

}
