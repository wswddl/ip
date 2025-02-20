package milo.command;

import milo.Storage;
import milo.Ui;
import milo.task.TaskList;

/**
 * Represents a command to exit the application.
 * This command triggers the UI to display a goodbye message.
 */
public class ExitCommand extends Command {
    private String commandResponse;

    /**
     * Executes the ExitCommand by triggering the UI to display a goodbye message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayGoodbye();
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
