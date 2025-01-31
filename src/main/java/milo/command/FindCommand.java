package milo.command;

import java.util.ArrayList;

import milo.Storage;
import milo.Ui;
import milo.task.Task;
import milo.task.TaskList;

/**
 * Represents a command to search for tasks containing a specific keyword.
 * When executed, this command searches through the task list and displays
 * all tasks that match the keyword.
 */
public class FindCommand extends Command {
    private final String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword
     * in their description, and then displays the matching tasks in UI.
     *
     * @param tasks The task list to search through.
     * @param ui The user interface to display the results.
     * @param storage The storage object for saving/loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> listOfTasks = tasks.findKeyword(keyword);
        ui.findKeywordUi(listOfTasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}

