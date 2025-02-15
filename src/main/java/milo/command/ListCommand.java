package milo.command;

import milo.Storage;
import milo.Ui;
import milo.task.Task;
import milo.task.TaskList;

/**
 * Represents a command to list all the tasks in task list.
 * This command triggers the UI to display the current list of tasks.
 */
public class ListCommand extends Command {

    private String commandResponse = "";

    /**
     * Executes the command to display the list of tasks in the UI and updates the changes to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasksUi(tasks);
        storage.updateTask(tasks);
        int idx = 1;
        if (tasks.size() == 0) {
            this.commandResponse = "There is no task in the list";
        } else {
            for (Task task : tasks.getList()) {
                // concat all the tasks in String format with next line
                this.commandResponse += idx + "." + task + "\n";
                idx++;
            }
        }
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
