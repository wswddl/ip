package milo.command;

import milo.Storage;
import milo.task.TaskList;

/**
 * Represents a command to greet the user.
 * This command triggers the UI to display a goodbye message.
 */
public class GreetCommand extends Command {
    private String commandResponse;

    /**
     * Executes the command to greet the user.
     * Sets the response message with a welcome greeting.
     *
     * @param tasks   The task list (not used in this command).
     * @param storage The storage system (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        String smilingEmoji = "\uD83D\uDE0A";
        this.commandResponse = "Hello! I'm Milo Ice\n" + "What can I do for you? " + smilingEmoji;
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
