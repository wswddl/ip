package milo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import milo.command.AddCommand;
import milo.command.ChangeDeadlineCommand;
import milo.command.Command;
import milo.command.DeleteCommand;
import milo.command.ExitCommand;
import milo.command.FindCommand;
import milo.command.GreetCommand;
import milo.command.ListCommand;
import milo.command.RescheduleEventCommand;
import milo.command.ToggleMarkCommand;
import milo.task.Deadline;
import milo.task.Event;
import milo.task.Task;
import milo.task.TaskList;
import milo.task.Todo;

/**
 * A parser that processes user input and returns the appropriate Command.
 * The input is parsed into different task-related commands including
 * listing, marking, unmarking, adding Todo task,
 * Deadline task, Event task, deleting task, finding keyword and exiting the application.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command.
     *
     * @param input The user input to be parsed.
     * @param tasks The current list of tasks to reference during parsing.
     * @return The appropriate Command based on the input.
     * @throws MiloIceException If the input is invalid or cannot be processed.
     */
    @SuppressWarnings("checkstyle:Regexp")
    public static Command parse(String input, TaskList tasks) throws MiloIceException {
        String[] parts = input.split(" ");
        Enum inputEnum = Enum.of(input);

        assert inputEnum != null;

        if (inputEnum == Enum.GREET) {
            return new GreetCommand();
        } else if (inputEnum == Enum.BYE) {
            return new ExitCommand();

        } else if (inputEnum == Enum.LIST) {
            return new ListCommand();

        } else if (inputEnum == Enum.DELETE) {
            return handleDeleteInput(parts, tasks);

        } else if (inputEnum == Enum.MARK) {
            return handleMarkCommand(parts, tasks);

        } else if (inputEnum == Enum.UNMARK) {
            return handleUnmarkCommand(parts, tasks);

        } else if (inputEnum == Enum.TODO) {
            return handleTodoCommand(input);

        } else if (inputEnum == Enum.DEADLINE) {
            return handleDeadlineCommand(input);

        } else if (inputEnum == Enum.EVENT) {
            return handleEventCommand(input);

        } else if (inputEnum == Enum.FIND) {
            String keyword = input.substring(4).trim();
            return new FindCommand(keyword);

        } else if (inputEnum == Enum.RESCHEDULE) {
            return handleRescheduleCommand(parts, tasks);
        } else {
            throw new MiloIceException("""
                    Sorry mate, I don't understand your request
                    Here's are the command you can use \uD83C\uDF89:
                    Time format: [yyyy-MM-dd HHmm]
                    
                    1. list/ls: view all tasks
                    2. bye: Exit chatbot
                    3. mark: mark the task with the given index as done
                       Example: mark 2
                    4. unmark: mark the task with the given index as NOT done
                       Example: unmark 3
                       
                    5. todo: add a todo task
                       Example: todo touch grass
                    6. deadline: add a task with a deadline
                       Example: deadline feel relive /by 2031-01-01 0100
                    7. event: add a task with start & end time
                       Example: event unburden myself /from 2031-01-01 0030 /to 2031-01-01 0100
                       
                    8. find: task with description that matches the given keyword (not case-sensitive)
                       Example: find <keyword>
                       
                    9. reschedule/res: change the deadline OR reschedule an Event, can't be used for Todo
                                   Format: res 1 [new deadline] (1st task is a Deadline)
                                   Example: res 1 2031-01-01 0100
                                   Format: res 2 [new start] [new end] (2nd task is an Event)
                                   Example: res 2 2031-01-01 0300 2031-01-01 0100""");
        }
    }

    private static DeleteCommand handleDeleteInput(String[] parts, TaskList tasks) throws MiloIceException {
        if (parts.length != 2) {
            throw new MiloIceException("Mate that's a wrong input, correct example: delete 1");
        }

        int deleteIndex;
        try {
            deleteIndex = Integer.parseInt(parts[1]); // might throw NumberFormatException
        } catch (NumberFormatException e) {
            throw new MiloIceException("Mate, the input must be a number (Eg. mark 1, unmark 1, delete 3)");
        }

        if (deleteIndex < 1 || deleteIndex > tasks.size()) {
            String laughEmoji = "\uD83D\uDE02";
            throw new MiloIceException("Invalid index mate: there are only " + tasks.size() + " task(s) " + laughEmoji);
        }

        return new DeleteCommand(deleteIndex);
    }

    private static ToggleMarkCommand handleMarkCommand(String[] parts, TaskList tasks) throws MiloIceException {
        if (parts.length != 2) {
            throw new MiloIceException("Invalid input mate: correct example \"mark 1\"");
        }

        int markIndex;
        try {
            markIndex = Integer.parseInt(parts[1]); // might throw NumberFormatException
        } catch (NumberFormatException e) {
            throw new MiloIceException("Input must be a number (Eg. mark 1, unmark 1, delete 3)");
        }

        if (markIndex < 1 || markIndex > tasks.size()) {
            String laughEmoji = "\uD83D\uDE02";
            throw new MiloIceException("Invalid index: there are only " + tasks.size() + " task(s) " + laughEmoji);
        }

        Task task = tasks.getTask(markIndex - 1);
        if (!task.markAsDone()) {
            throw new MiloIceException("This task has already been marked before");
        }

        return new ToggleMarkCommand(Enum.MARK, markIndex);
    }

    private static ToggleMarkCommand handleUnmarkCommand(String[] parts, TaskList tasks) throws MiloIceException {
        if (parts.length != 2) {
            throw new MiloIceException("Invalid input mate: correct example \"mark 1\"");
        }

        int unmarkIndex;
        try {
            unmarkIndex = Integer.parseInt(parts[1]); // might throw NumberFormatException
        } catch (NumberFormatException e) {
            throw new MiloIceException("Input must be a number (Eg. mark 1, unmark 1, delete 3)");
        }

        if (unmarkIndex < 1 || unmarkIndex > tasks.size()) {
            String laughEmoji = "\uD83D\uDE02";
            throw new MiloIceException("Invalid index: there are only " + tasks.size() + " task(s) " + laughEmoji);
        }

        Task task = tasks.getTask(unmarkIndex - 1);
        if (!task.unmarkAsDone()) {
            throw new MiloIceException("This task isn't marked yet");
        }

        return new ToggleMarkCommand(Enum.UNMARK, unmarkIndex);
    }

    private static AddCommand handleTodoCommand(String input) throws MiloIceException {
        int byIdx = input.indexOf("/by");
        int fromIdx = input.indexOf("/from");
        int toIdx = input.indexOf("/to");
        String description = input.substring(4).trim();

        if (description.isEmpty()) {
            String sadEmoji = "\uD83D\uDE22";
            throw new MiloIceException("Invalid input mate: description of todo cannot be empty" + sadEmoji);
        } else if (byIdx != -1 || fromIdx != -1 || toIdx != -1) {
            // don't allow /by /from /to in To_do
            String sadEmoji = "\uD83D\uDE22";
            throw new MiloIceException("Invalid input mate: Todo should not contain '/by', '/from', '/to'" + sadEmoji);
        }
        // happy path
        Todo todo = new Todo(description, false);
        return new AddCommand(todo);
    }

    private static AddCommand handleDeadlineCommand(String input) throws MiloIceException {
        int byIdx = input.indexOf(" /by ");

        if (byIdx == -1) {
            throw new MiloIceException(
                    "Please specify the deadline using '/by'\n"
                            + "Example: deadline finish cs2103t project /by [yyyy-MM-dd HHmm]");
        }

        String description = input.substring(8, byIdx).trim();
        String deadline = input.substring(byIdx + 4).trim();
        if (description.isEmpty() || deadline.isEmpty()) {
            String sadEmoji = "\uD83D\uDE22";
            throw new MiloIceException("Invalid input: description and deadline cannot be empty" + sadEmoji);
        }
        // happy path
        Deadline dl = Deadline.of(description, false, deadline);
        return new AddCommand(dl);
    }

    private static AddCommand handleEventCommand(String input) throws MiloIceException {
        int fromIdx = input.indexOf(" /from ");
        int toIdx = input.indexOf(" /to ");

        if (fromIdx == -1 || toIdx == -1) {
            throw new MiloIceException(
                    "Please specify the start date using '/from' and end date using '/to'\n"
                            + "Example: event [description] /from [yyyy-MM-dd HHmm] /to [yyyy-MM-dd HHmm]");
        }

        String description = input.substring(5, fromIdx).trim();
        String start = input.substring(fromIdx + 6, toIdx).trim();
        String end = input.substring(toIdx + 4).trim();
        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            String sadEmoji = "\uD83D\uDE22";
            throw new MiloIceException("Invalid input: description, start and end cannot be empty" + sadEmoji);
        }
        // happy path
        Event event = Event.of(description, false, start, end);
        return new AddCommand(event);
    }

    private static Command handleRescheduleCommand(
            String[] parts, TaskList tasks) throws MiloIceException {

        if (parts.length < 2) {
            throw new MiloIceException("Input should be: res [index number] [new time]");
        }

        int rescheduleIndex;
        try {
            rescheduleIndex = Integer.parseInt(parts[1]); // might throw NumberFormatException
        } catch (NumberFormatException e) {
            throw new MiloIceException("Input should be: res [index NUMBER] [new time]");
        }
        // index out of bound
        if (rescheduleIndex < 1 || rescheduleIndex > tasks.size()) {
            String laughEmoji = "\uD83D\uDE02";
            throw new MiloIceException("Invalid index: there are only " + tasks.size() + " task(s) "+ laughEmoji);
        }

        Task task = tasks.getTask(rescheduleIndex - 1);
        if (task instanceof Deadline && parts.length != 4) {
            // give instruction that follows the index number the user provided :)
            throw new MiloIceException("Invalid input format for rescheduling a deadline\n"
                            + "Correct format: res [index] [new deadline]\n"
                            + "Example: res " + rescheduleIndex + " 2027-01-01 0100 "
                            + "(task " + rescheduleIndex + " is a Deadline)");

        } else if (task instanceof Deadline) {
            Deadline dl = (Deadline) task;
            String newStringDeadline = parts[2] + " " + parts[3];
            return new ChangeDeadlineCommand(dl, newStringDeadline);


        } else if (task instanceof Event && parts.length != 6) {
            // give instruction that follows the index number the user provided :)
            throw new MiloIceException("Invalid input format for rescheduling an event:\n"
                            + "Correct format: res [index] [new start] [new end]\n"
                            + "Example: res " + rescheduleIndex + " 2031-01-01 0030 2031-01-01 0100 "
                            + "(task " + rescheduleIndex + " is an Event)");

        } else if (task instanceof Event) {
            Event event = (Event) task;
            String newStringStart = parts[2] + " " + parts[3];
            String newStringEnd = parts[4] + " " + parts[5];
            return new RescheduleEventCommand(event, newStringStart, newStringEnd);


        } else {
            // task is a todo object
            throw new MiloIceException("Can only reschedule Deadline and Event, not Todo task");
        }
    }


    /**
     * Parses the given file, create the tasks and loads those tasks into the TaskList.
     * It handles three task types: Todo, Deadline, and Event.
     * If a task is invalid, it is skipped, and the number of corrupted tasks is counted.
     *
     * @param file The file to be parsed.
     * @param tasks The TaskList to add the tasks to.
     * @return The number of corrupted tasks.
     */
    public static int parseFileAndLoadTask(File file, TaskList tasks) {
        int numOfCorruptedFiles = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (file.exists() && scanner.hasNextLine()) {
                String savedTextLine = scanner.nextLine();
                String[] parts = savedTextLine.split(" \\| ");

                if (parts[0].equals("T") && parts.length == 3) {
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    try {
                        Task todo = Todo.of(description, isDone);
                        tasks.addTask(todo);
                    } catch (MiloIceException e) {
                        numOfCorruptedFiles++;
                    }

                } else if (parts[0].equals("D") && parts.length == 4) {
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    String deadline = parts[3];
                    try {
                        Task dl = Deadline.of(description, isDone, deadline);
                        tasks.addTask(dl);
                    } catch (MiloIceException e) {
                        numOfCorruptedFiles++;
                    }

                } else if (parts[0].equals("E") && parts.length == 5) {
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    String start = parts[3];
                    String end = parts[4];
                    try {
                        Task event = Event.of(description, isDone, start, end);
                        tasks.addTask(event);
                    } catch (MiloIceException e) {
                        numOfCorruptedFiles++;
                    }
                }
                // ignore the corrupted task and move to the next one
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
        return numOfCorruptedFiles;
    }
}
