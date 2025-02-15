package milo.command;

import java.util.ArrayList;
import java.util.stream.Stream;

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
    private String commandResponse;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword
     * in their description, and then displays the matching tasks in UI.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> listOfTasksWithKeyword = tasks.findKeyword(keyword);
        ui.findKeywordUi(listOfTasksWithKeyword);

        if (!listOfTasksWithKeyword.isEmpty()) {
            this.commandResponse = "Here are the matching tasks in your list:\n";
            // Try to use stream for fun
            this.commandResponse += Stream.iterate(1, i -> i <= listOfTasksWithKeyword.size(), i -> i + 1)
                    .map(idx -> idx + ". " + listOfTasksWithKeyword.get(idx - 1) + "\n")
                    .reduce("", (acc, s) -> acc + s);
        } else {
            this.commandResponse = "Sorry, there is no matching tasks in your list";
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

