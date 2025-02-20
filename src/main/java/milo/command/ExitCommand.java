package milo.command;

import milo.Storage;
import milo.task.TaskList;

/**
 * Represents a command to exit the application.
 * This command triggers the UI to display a goodbye message.
 */
public class ExitCommand extends Command {
    private String commandResponse;

    /**
     * Executes the command by setting a goodbye message.
     * This message will be displayed before the application exits.
     *
     * @param tasks   The task list (not used in this command).
     * @param storage The storage system (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        String smilingEmoji = "\uD83D\uDE0A";
        this.commandResponse = "Bye. Hope to see you again soon! " + smilingEmoji;
    }

    @Override
    public boolean isExit() {
        return true;
    }
    @Override
    public String getCommandResponse() {
        return this.commandResponse;
    }
}
