package milo.command;

import java.util.ArrayList;
import java.util.stream.Stream;

import milo.Storage;
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
     * Searches for tasks containing the keyword and updates the response message.
     *
     * @param tasks   The task list to search.
     * @param storage The storage system (not modified).
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        ArrayList<Task> listOfTasksWithKeyword = tasks.findKeyword(keyword);

        if (!listOfTasksWithKeyword.isEmpty()) {
            this.commandResponse = "Here are the matching tasks in your list mate:\n";
            // Try to use stream for fun
            this.commandResponse += Stream.iterate(1, i -> i <= listOfTasksWithKeyword.size(), i -> i + 1)
                    .map(idx -> idx + ". " + listOfTasksWithKeyword.get(idx - 1) + "\n")
                    .reduce("", (acc, s) -> acc + s);
        } else {
            this.commandResponse = "Sorry mate, there is no matching tasks in your list";
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

