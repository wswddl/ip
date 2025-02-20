package milo.command;

import milo.Storage;
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
    private String commandResponse;
    public AddCommand(Task addedTask) {
        assert addedTask != null : "added task cannot be null";
        this.addedTask = addedTask;
    }

    /**
     * Executes the AddCommand to add the task to the task list, updates the UI,
     * and the changes to storage.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addTask(this.addedTask);
        storage.updateTask(tasks);

        this.commandResponse = "Okie boss. I've added this task:\n" + addedTask + "\n"
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
