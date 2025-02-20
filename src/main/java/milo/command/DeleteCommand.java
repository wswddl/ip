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
    private String commandResponse;
    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }
    /**
     * Executes the DeleteCommand by removing the task from the task list,
     * updates the UI to show the task deletion, and updates the changes to the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task removedTask = tasks.removeTask(deleteIndex - 1); ///// remember to change when using TaskList
        ui.deleteTaskUi(removedTask, tasks);
        storage.updateTask(tasks);

        this.commandResponse = "Okie, I've removed this task:\n"
                + removedTask + "\n"
                + "Now you have " + tasks.size() + " task(s) in the list";
    }

    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public String getCommandResponse() {
        return this.commandResponse;
    }
}
