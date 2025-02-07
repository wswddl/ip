package milo.command;

import milo.Storage;
import milo.Ui;
import milo.task.TaskList;

/**
 * Abstract class representing a command.
 * Each specific command (e.g., AddCommand, ListCommand) extends this class
 * and provides the implementation for the `execute` method to perform
 * the command's action. The `isExit` method is used to determine if the
 * command triggers an exit action for the application.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
    public abstract String getCommandResponse();
}
