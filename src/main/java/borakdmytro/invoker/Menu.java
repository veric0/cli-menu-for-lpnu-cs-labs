package borakdmytro.invoker;

import borakdmytro.command.Command;
import borakdmytro.receiver.Receiver;
import borakdmytro.util.MenuInputReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores menu items. Extends {@link MenuItem} so that you can create submenus.
 */
public class Menu extends MenuItem {
    private final List<MenuItem> menuItems;

    private Menu(String title, Receiver app, List<MenuItem> menuItems) {
        super(title, app, null);
        this.menuItems = menuItems;
    }

    /**
     * Shows all available menu items. In infinite loop shows the start menu after the action is finished.
     */
    public void show() {
        while (true) {
            doAction();
        }
    }

    /**
     * Prints all available menu items. Calls next selected by the user {@link MenuItem}.
     */
    @Override
    protected void doAction() {
//        super.doAction(); // todo additional action in submenu
        StringBuilder sb = new StringBuilder();
        sb.append("\n ").append(getText()).append(":");
        int i = 0;
        for (MenuItem menuItem: menuItems) {
            sb.append("\n ").append(++i).append(" - ");
            sb.append(menuItem.getText());
        }
        System.out.println(sb);
        int choice = MenuInputReader.readInteger(1, menuItems.size()) - 1;
        MenuItem menuItem = menuItems.get(choice);
        menuItem.doAction();
    }

    public static MenuBuilder builder() {
        return new MenuBuilder();
    }

    public static class MenuBuilder extends MenuItemBuilder {
        private String title;
        private final List<MenuItem> menuItems;
        private Receiver app;
        private boolean isReturnBackMenu;
        private boolean isExitMenu;

        private MenuBuilder() {
            this.title = "Choose menu item";
            this.menuItems = new ArrayList<>();
            this.app = null;
            this.isReturnBackMenu = false;
            this.isExitMenu = false;
        }

        private static final MenuItem returnBackMenuItem = MenuItem.builder()
                .setText("Return back")
                .setAction(new ReturnBackCommand())
                .build();
        private static final MenuItem exitMenuItem = MenuItem.builder()
                .setText("Exit")
                .setAction(new ExitCommand())
                .build();

        @Override
        public MenuBuilder setText(String title) {
            this.title = title;
            return this;
        }

        @Override
        public MenuBuilder setAction(Command action) {
            super.setAction(null);
            return this;
        }

        public MenuBuilder setApp(Receiver app) {
            this.app = app;
            return this;
        }

        public MenuBuilder addMenuItem(MenuItem menuItem) {
            this.menuItems.add(menuItem);
            return this;
        }

        public MenuBuilder addReturnBack() {
            isReturnBackMenu = true;
            return this;
        }

        public MenuBuilder addExitMenu() {
            isExitMenu = true;
            return this;
        }

        /**
         * {@link Menu.MenuBuilder#returnBackMenuItem} and {@link Menu.MenuBuilder#exitMenuItem} are in the end of menu.
         * @return created {@link Menu}.
         */
        @Override
        public Menu build() {
            if (isReturnBackMenu) {
                menuItems.add(returnBackMenuItem);
            }
            if (isExitMenu) {
                menuItems.add(exitMenuItem);
            }
            menuItems.forEach(menuItem -> menuItem.setReceiver(app));
            return new Menu(title, app, menuItems);
        }
    }

    private static class ReturnBackCommand implements Command {
        @Override
        public void execute(Receiver app) {
            System.out.println("ReturnBackCommand"); // todo ReturnBackCommand
        }
    }

    private static class ExitCommand implements Command {
        @Override
        public void execute(Receiver app) {
            System.exit(0);
        }
    }

    private static class DoNothingCommand implements Command {
        @Override
        public void execute(Receiver app) {
//            System.out.println("DoNothingCommand");
        }
    }
}
