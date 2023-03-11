package borakdmytro.command;

import borakdmytro.receiver.Receiver;

/**
 * Commands. Calls the appropriate method of the receiver.
 * TO DO: additional data can be read from the console and passed as a parameter to receiver.
 */
public interface Command {
    void execute(Receiver app);
}
