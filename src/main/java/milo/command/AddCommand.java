package milo.command;

import milo.Storage;
import milo.Ui;
import milo.task.Task;
import milo.task.TaskList;

/**
 * Represents a command to add a task to the task list.
 * This command takes in a Task and adds it to the TaskList,
 * updates the UI to display the added task and updates the changes
 * to storage.
 */
public class AddCommand extends Command {

    private final Task addedTask;
    public AddCommand(Task addedTask) {
        this.addedTask = addedTask;
    }

    /**
     * Executes the AddCommand to add the task to the task list, updates the UI,
     * and the changes to storage.
     *
     * @param tasks   The TaskList to add the task to.
     * @param ui      The UI instance to update the UI with the new task.
     * @param storage The storage instance to update the saved tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.addedTask);
        ui.addTaskUi(this.addedTask, tasks);
        storage.updateTask(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
