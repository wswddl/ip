package milo.command;

import milo.MiloIceException;
import milo.Storage;
import milo.task.Event;
import milo.task.TaskList;

/**
 * Command to reschedule an event by changing its start and end time.
 */
public class RescheduleEventCommand extends Command {

    private String commandResponse;
    private final Event event;
    private final String newStartInUserInputFormat;
    private final String newEndInUserInputFormat;

    /**
     * Constructs a RescheduleEventCommand object with the event to be rescheduled and the new start and end times.
     *
     * @param event The event to be rescheduled.
     * @param newStartInUserInputFormat The new start time in user input format.
     * @param newEndInUserInputFormat The new end time in user input format.
     */
    public RescheduleEventCommand(
            Event event, String newStartInUserInputFormat, String newEndInUserInputFormat) {
        this.event = event;
        this.newStartInUserInputFormat = newStartInUserInputFormat;
        this.newEndInUserInputFormat = newEndInUserInputFormat;
    }

    /**
     * Executes the command to reschedule the event by changing its start and end times and provide a response.
     *
     * @throws MiloIceException If there is an error while updating the event.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws MiloIceException {
        String oldStartInChatFormat = event.getStartTime();
        String oldEndInChatFormat = event.getEndTime();
        String[] newStartAndEndInChatFormat = event.changeStartAndEndTime(
                newStartInUserInputFormat, newEndInUserInputFormat);
        String newStartInChatFormat = newStartAndEndInChatFormat[0];
        String newEndInChatFormat = newStartAndEndInChatFormat[1];
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


