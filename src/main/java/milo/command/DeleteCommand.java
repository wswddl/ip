package milo.command;

import milo.Storage;
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
     * Executes the command by removing the task at the specified index
     * and save the changes to storage.
     *
     * @param tasks   The task list from which the task is to be removed.
     * @param storage The storage system to persist changes.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Task removedTask = tasks.removeTask(deleteIndex - 1); ///// remember to change when using TaskList
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
