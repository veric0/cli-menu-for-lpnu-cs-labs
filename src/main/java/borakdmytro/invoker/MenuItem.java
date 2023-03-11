package borakdmytro.invoker;

import borakdmytro.command.Command;
import borakdmytro.receiver.Receiver;

/**
 * Invoker. Calls the command that was given to him. The last layer of menu.
 */
public class MenuItem {
    private final String text;
    private Receiver receiver;
    private final Command action;

    MenuItem(String text, Receiver receiver, Command action) {
        this.text = text;
        this.receiver = receiver;
        this.action = action;
    }

    /**
     * @return name of menu title
     */
    public String getText() {
        return text;
    }

    /**
     * invokes command
     */
    protected void doAction() {
        if (action != null && receiver != null) {
            action.execute(receiver);
        }/* else {
            System.out.println("NO ACTION");
        }*/
    }

    /**
     * changes receiver
     * @param receiver app
     */
    protected void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public static MenuItemBuilder builder() {
        return new MenuItemBuilder();
    }

    public static class MenuItemBuilder {
        private String text;
        private Command action;

        protected MenuItemBuilder() {
            this.text = null;
            this.action = null;
        }

        public MenuItemBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public MenuItemBuilder setAction(Command action) {
            this.action = action;
            return this;
        }

        public MenuItem build() {
            return new MenuItem(text, null, action); // receiver is set by Menu
        }
    }
}
