package borakdmytro.client;

import borakdmytro.invoker.Menu;
import borakdmytro.invoker.MenuItem;
import borakdmytro.receiver.AppExample;
import borakdmytro.receiver.Receiver;
import borakdmytro.util.MenuIO;

/**
 * Client - entry point. Creates app and menu instances.
 */
public class Main {
    public static void main(String[] args) {
        Receiver app = new AppExample(); // Lamp
        Menu menu = Menu.builder()
                .setApp(app)
                .setTitle("Main menu")
                .addMenuItem(MenuItem.builder()
                        .setText("Turn On")
                        .setAction(Receiver::PowerOn)
                        .build())
                .addMenuItem(MenuItem.builder()
                        .setText("Turn Off")
                        .setAction(Receiver::PowerOff)
                        .build())
                .addMenuItem(Menu.builder()
//                        .setApp(app)
                        .setText("Change color")
                        .setTitle("Choose color")
                        .addMenuItem(Menu.builder()
//                                .setApp(app)
                                .setText("White")
                                .setTitle("Select the white color temperature")
                                .addMenuItem(MenuItem.builder()
                                        .setText("3000 K")
                                        .setAction(app1 -> app1.setColor("Warm white"))
                                        .build())
                                .addMenuItem(MenuItem.builder()
                                        .setText("5000 K")
                                        .setAction(app1 -> app1.setColor("Cold white"))
                                        .build())
                                .addReturnBack()
                                .build())
                        .addMenuItem(MenuItem.builder()
                                .setText("Red")
                                .setAction(app1 -> app1.setColor("Red"))
                                .build())
                        .addMenuItem(MenuItem.builder()
                                .setText("Blue")
                                .setAction(app1 -> app1.setColor("Blue"))
                                .build())
                        .addMenuItem(MenuItem.builder()
                                .setText("Green")
                                .setAction(app1 -> app1.setColor("Green"))
                                .build())
                        .addMenuItem(MenuItem.builder()
                                .setText("Custom color")
                                .setAction(app1 -> {
                                    MenuIO.write("Input red value:");
                                    int r = MenuIO.readInteger(0, 255);
                                    MenuIO.write("Input green value:");
                                    int g = MenuIO.readInteger(0, 255);
                                    MenuIO.write("Input blue value:");
                                    int b = MenuIO.readInteger(0, 255);
                                    app1.setColor(String.format("rgb(%d, %d, %d)", r, g, b));
                                })
                                .build())
                        .addReturnBack()
                        .build())
                .addMenuItem(MenuItem.builder()
                        .setText("View current color")
                        .setAction(Receiver::viewCurrentColor)
                        .build())
                .addExitMenu()
                .build();
        menu.show();
    }
}