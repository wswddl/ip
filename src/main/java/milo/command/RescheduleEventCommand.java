package milo.command;

import milo.MiloIceException;
import milo.Storage;
import milo.Ui;
import milo.task.Event;
import milo.task.TaskList;

/**
 * Command to reschedule an event by changing its start and end time.
 */
public class RescheduleEventCommand extends Command {

    private String commandResponse;
    private final Event event;
    private final String newStartInUserFormat;
    private final String newEndInUserFormat;

    /**
     * Constructs a RescheduleEventCommand object with the event to be rescheduled and the new start and end times.
     *
     * @param event The event to be rescheduled.
     * @param newStartInUserFormat The new start time in user input format.
     * @param newEndInUserFormat The new end time in user input format.
     */
    public RescheduleEventCommand(
            Event event, String newStartInUserFormat, String newEndInUserFormat) {
        this.event = event;
        this.newStartInUserFormat = newStartInUserFormat;
        this.newEndInUserFormat = newEndInUserFormat;
    }

    /**
     * Executes the command to reschedule the event by changing its start and end times and provide a response.
     *
     * @throws MiloIceException If there is an error while updating the event.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MiloIceException {
        String oldStartInChatFormat = event.getStartTime();
        String oldEndInChatFormat = event.getEndTime();
        String newStartInChatFormat = event.changeStartTime(newStartInUserFormat);
        String newEndInChatFormat = event.changeEndTime(newEndInUserFormat);
        this.commandResponse = "Nice! Ive changed the schedule \n"
                + "Old: " + oldStartInChatFormat + " -- " + oldEndInChatFormat + "\n"
                + "New: " + newStartInChatFormat + " -- " + newEndInChatFormat + "\n"
                + event;
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


