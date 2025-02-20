package milo.command;

import milo.Storage;
import milo.task.Task;
import milo.task.TaskList;

/**
 * Represents a command to list all the tasks in task list.
 * This command triggers the UI to display the current list of tasks.
 */
public class ListCommand extends Command {

    private String commandResponse = "";

    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param tasks   The task list containing the tasks.
     * @param storage The storage system to update the tasks.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        storage.updateTask(tasks);
        int idx = 1;
        if (tasks.size() == 0) {
            this.commandResponse = "There is no task in the list mate";
        } else {
            String smilingEmoji = "\uD83D\uDE0A";
            this.commandResponse = "Here are all the tasks you have " + smilingEmoji + "\n\n";
            for (Task task : tasks.getList()) {
                // concat all the tasks in String format with next line
                this.commandResponse += idx + ". " + task + "\n";
                idx++;
            }

            if (tasks.size() == 1) {
                String happyEmoji = "\uD83D\uDE00";
                this.commandResponse += "\nOne more to go!!! " + happyEmoji;
            } else if (tasks.size() < 4) {
                String happyEmoji = "\uD83D\uDE00";
                this.commandResponse += "\nNice! you almost finish all your tasks " + happyEmoji;
            } else {
                String scaredEmoji = "\uD83D\uDE28";
                this.commandResponse += "\nWow! that's quite a lot of tasks mate " + scaredEmoji;
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
