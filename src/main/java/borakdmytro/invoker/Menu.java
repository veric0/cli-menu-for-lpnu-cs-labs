package borakdmytro.invoker;

import borakdmytro.command.Command;
import borakdmytro.receiver.Receiver;
import borakdmytro.util.MenuIO;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores menu items. Extends {@link MenuItem} so that you can create submenus.
 */
public class Menu extends MenuItem {
    private final List<MenuItem> menuItems;
    private final String title;
    private final boolean isHasReturnBack;

    private Menu(String title, String text, Receiver app, List<MenuItem> menuItems, boolean isHasReturnBack) {
        super(text, app, null);
        this.menuItems = menuItems;
        this.title = title;
        this.isHasReturnBack = isHasReturnBack;
    }

    /**
     * Shows all available menu items. In infinite loop shows the start menu after the action is finished.
     */
    public void show() {
        while (true) {
            doAction();
        }
    }

    public String getTitle() {
        return title;
    }

    /**
     * Prints all available menu items. Calls next selected by the user {@link MenuItem}.
     */
    @Override
    protected void doAction() {
//        super.doAction(); // todo additional action in submenu
        StringBuilder sb = new StringBuilder();
        sb.append("\n ").append(getTitle()).append(":");
        int i = 0;
        for (MenuItem menuItem: menuItems) {
            sb.append("\n ").append(++i).append(" - ");
            sb.append(menuItem.getText());
        }
        MenuIO.write(sb.toString());
        int choice = MenuIO.readInteger(1, menuItems.size()) - 1;
        MenuItem menuItem = menuItems.get(choice);
        menuItem.doAction();
    }

    @Override
    protected void setReceiver(Receiver receiver) {
        menuItems.forEach(menuItem -> menuItem.setReceiver(receiver));
        super.setReceiver(receiver);
    }

    public static MenuBuilder builder() {
        return new MenuBuilder();
    }

    public static class MenuBuilder extends MenuItemBuilder {
        private String title;
        private String text;
        private final List<MenuItem> menuItems;
        private Receiver app;
        private boolean isReturnBackMenu;
        private boolean isExitMenu;

        private MenuBuilder() {
            this.title = "Choose menu item";
            this.text = "Submenu";
            this.menuItems = new ArrayList<>();
            this.app = null;
            this.isReturnBackMenu = false;
            this.isExitMenu = false;
        }

        /**
         * @param title title of menu
         */
        public MenuBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * @param text text where this menu is submenu
         */
        @Override
        public MenuBuilder setText(String text) {
            this.text = text;
            return this;
        }

        @Override
        public MenuBuilder setAction(Command action) {
            super.setAction(null); // todo additional action in submenu
            return this;
        }

        /**
         * Can be not used in the submenu. By default, the app from the parent menu will be used.
         * @param app app which will perform actions in commands
         */
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
         * "return to previous menu" and "exit program" are in the end of menu.
         * @return created {@link Menu}.
         */
        @Override
        public Menu build() {
            Menu menu = new Menu(title, text, app, menuItems, isReturnBackMenu);
            if (isExitMenu) {
                MenuItem exitMenuItem = MenuItem.builder()
                    .setText("Exit")
                    .setAction(new ExitCommand())
                    .build();
                menuItems.add(exitMenuItem);
            }
            MenuItem returnBackMenuItem = MenuItem.builder()
                    .setText("Return back")
                    .setAction(new ReturnBackCommand(menu))
                    .build();
            for (MenuItem menuItem: menuItems) {
                menuItem.setReceiver(app);
                if (menuItem.getClass() == menu.getClass() && ((Menu) menuItem).isHasReturnBack) {
                    ((Menu) menuItem).menuItems.add(returnBackMenuItem);
                }
            }
            return menu;
        }
    }

    private static class ReturnBackCommand implements Command {
        private final Menu prevMenu;
        public ReturnBackCommand(Menu prevMenu) {
            this.prevMenu = prevMenu;
        }

        @Override
        public void execute(Receiver app) {
            prevMenu.doAction();
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
