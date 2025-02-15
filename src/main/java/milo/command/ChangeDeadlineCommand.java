package milo.command;

import milo.MiloIceException;
import milo.Storage;
import milo.Ui;
import milo.task.Deadline;
import milo.task.TaskList;

/**
 * Command to change the deadline of a task.
 */
public class ChangeDeadlineCommand extends Command {

    private String commandResponse;
    private final Deadline dl;
    private final String newDeadlineInUserFormat;

    /**
     * Constructs a ChangeDeadlineCommand object with the deadline to be changed and the new deadline.
     *
     * @param dl The deadline to be changed.
     * @param newDeadlineInUserFormat The new deadline in user input format.
     */
    public ChangeDeadlineCommand(Deadline dl, String newDeadlineInUserFormat) {
        this.dl = dl;
        this.newDeadlineInUserFormat = newDeadlineInUserFormat;
    }

    /**
     * Executes the command to change the deadline and provide a response.
     *
     * @throws MiloIceException If there is an error while updating the event.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MiloIceException {
        String oldDeadlineInChatFormat = dl.getDeadline();
        String newDeadlineInChatFormat = dl.changeDeadline(newDeadlineInUserFormat);
        this.commandResponse = "Nice! Ive changed the deadline from " + oldDeadlineInChatFormat + " to "
                + newDeadlineInChatFormat + "\n"
                + dl;
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

