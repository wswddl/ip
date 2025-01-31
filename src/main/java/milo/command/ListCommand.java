package milo.command;

import milo.Storage;
import milo.Ui;
import milo.task.TaskList;

/**
 * Represents a command to list all the tasks in task list.
 * This command triggers the UI to display the current list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display the list of tasks in the UI and updates the changes to storage.
     *
     * @param tasks    The list of tasks to be displayed.
     * @param ui       The user interface to interact with the user.
     * @param storage  The storage to save any changes made to the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasksUi(tasks);
        storage.updateTask(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
