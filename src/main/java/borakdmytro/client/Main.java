package borakdmytro.client;

import borakdmytro.invoker.Menu;
import borakdmytro.invoker.MenuItem;
import borakdmytro.receiver.AppExample;
import borakdmytro.receiver.Receiver;

/**
 * Client - entry point. Creates app and menu instances.
 */
public class Main {
    public static void main(String[] args) {
        Receiver app = new AppExample();
        Menu menu = Menu.builder()
                .setApp(app)
                .setText("Main menu")
                .addMenuItem(MenuItem.builder()
                        .setText("Turn On")
                        .setAction(Receiver::PowerOn)
                        .build())
                .addMenuItem(MenuItem.builder()
                        .setText("Turn Off")
                        .setAction(Receiver::PowerOff)
                        .build())
                .addMenuItem(Menu.builder()
                        .setApp(app)
                        .setText("Choose color")
                        .addMenuItem(MenuItem.builder()
                                .setText("White")
                                .setAction(app1 -> app1.setColor("White"))
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
                        .addReturnBack()
                        .build())
                .addExitMenu()
                .build();
        menu.show();
    }
}