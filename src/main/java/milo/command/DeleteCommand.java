package milo.command;

import milo.Storage;
import milo.Ui;
import milo.task.Task;
import milo.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 * This command takes in an index and remove the task corresponding to the index in the TaskList,
 * updates the UI to display the added task and updates the changes
 * to storage.
 */
public class DeleteCommand extends Command {
    private final int deleteIndex;
    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }
    /**
     * Executes the DeleteCommand by removing the task from the task list,
     * updates the UI to show the task deletion, and updates the changes to the storage.
     *
     * @param tasks   The TaskList from which the task will be removed.
     * @param ui      The UI instance to update with the task deletion.
     * @param storage The storage instance to save the changes to the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task removedTask = tasks.removeTask(deleteIndex - 1); ///// remember to change when using TaskList
        ui.deleteTaskUi(removedTask, tasks);
        storage.updateTask(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
