package milo.command;

import milo.Enum;
import milo.Storage;
import milo.task.Task;
import milo.task.TaskList;

/**
 * Represents a command to toggle the mark (mark or unmark) status of a task in the task list.
 * This command uses the Enum to mark or unmark a task based on the Enum (MARK or UNMARK).
 * The command updates the task list, UI, and updates the changes to storage.
 */
public class ToggleMarkCommand extends Command {
    private final int toggleMarkIndex;
    private String commandResponse;
    private final Enum taskEnum;

    /**
     * Constructor for the ToggleMarkCommand class.
     * Initializes the command with the task enum (MARK or UNMARK) and task index.
     *
     * @param taskEnum        The enum value representing whether the task should be marked or unmarked.
     * @param toggleMarkIndex The index of the task to be marked or unmarked in the task list.
     */
    public ToggleMarkCommand(Enum taskEnum, int toggleMarkIndex) {
        this.taskEnum = taskEnum;
        this.toggleMarkIndex = toggleMarkIndex;
    }

    /**
     * Executes the command to mark or unmark a task based on the taskEnum value.
     * It retrieves the task from the TaskList by the toggleMarkIndex field, toggles its status
     * and updates the UI and the changes to storage.
     *
     * @param tasks   The task list containing the tasks.
     * @param storage The storage system to update the tasks.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {

        if (taskEnum == Enum.MARK) {
            Task markTask = tasks.getTask(toggleMarkIndex - 1);
            String smilingEmoji = "\uD83D\uDE0A";
            this.commandResponse = "Nice! I've marked this task as done: " + smilingEmoji + "\n"
                    + markTask;

        } else if (taskEnum == Enum.UNMARK) {
            Task unmarkTask = tasks.getTask(toggleMarkIndex - 1);
            String smilingEmoji = "\uD83D\uDE0A";
            this.commandResponse = "Nice! I've marked this task as NOT done: " + smilingEmoji + "\n"
                    + unmarkTask;
        }
        storage.updateTask(tasks);
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
