package milo.command;

import milo.Enum;
import milo.Storage;
import milo.Ui;
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
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI instance used to display the task's mark/unmark status.
     * @param storage The storage instance used to persist the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        if (taskEnum == Enum.MARK) {
            Task markTask = tasks.getTask(toggleMarkIndex - 1);
            ui.markTaskUi(markTask);
            this.commandResponse = "Nice! I've marked this task as done:\n"
                    + markTask;

        } else if (taskEnum == Enum.UNMARK) {
            Task unmarkTask = tasks.getTask(toggleMarkIndex - 1);
            ui.unmarkTaskUi(unmarkTask);
            this.commandResponse = "Nice! I've marked this task as done:\n"
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
