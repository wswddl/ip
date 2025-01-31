package milo.command;

import milo.Storage;
import milo.Ui;
import milo.task.TaskList;

/**
 * Represents a command to exit the application.
 * This command triggers the UI to display a goodbye message.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand by triggering the UI to display a goodbye message.
     *
     * @param tasks   The TaskList, not used in this command.
     * @param ui      The UI instance to display the goodbye message.
     * @param storage The storage instance, not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
