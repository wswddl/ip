package milo.command;

import milo.MiloIceException;
import milo.Storage;
import milo.task.TaskList;

/**
 * Abstract class representing a command.
 * Each specific command (e.g., AddCommand, ListCommand) extends this class
 * and provides the implementation for the `execute` method to perform
 * the command's action. The `isExit` method is used to determine if the
 * command triggers an exit action for the application.
 */
public abstract class Command {

    /**
     * @param tasks   The TaskList, not used in this command.
     * @param storage The storage instance, not used in this command.
     * @throws MiloIceException
     */
    public abstract void execute(TaskList tasks, Storage storage) throws MiloIceException;
    public abstract boolean isExit();
    public abstract String getCommandResponse();
}
