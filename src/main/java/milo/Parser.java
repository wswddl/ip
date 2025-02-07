package milo;

import milo.command.AddCommand;
import milo.command.Command;
import milo.command.DeleteCommand;
import milo.command.ExitCommand;
import milo.command.FindCommand;
import milo.command.ListCommand;
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
     * @throws NumberFormatException If an invalid number is provided for task indices.
     */
    public static Command parse(String input, TaskList tasks) throws MiloIceException, NumberFormatException {
        String[] parts = input.split(" ");
        Enum inputEnum = Enum.of(input);

        if (inputEnum == Enum.BYE) {
            return new ExitCommand();

        } else if (inputEnum == Enum.LIST) {
            return new ListCommand();

        } else if (inputEnum == Enum.DELETE) {
            if (parts.length != 2) {
                throw new MiloIceException("Invalid input, correct example: delete 1");
            }
            int deleteIndex = Integer.parseInt(parts[1]);
            if (deleteIndex < 1 || deleteIndex > tasks.size()) {
                throw new MiloIceException("Invalid index: there are only " + tasks.size() + " task(s)");
            }
            return new DeleteCommand(deleteIndex);

        } else if (inputEnum == Enum.MARK) {
            if (parts.length != 2) {
                throw new MiloIceException("Invalid input, correct example: mark 1");
            }
            int markIndex = Integer.parseInt(parts[1]); // might throw NumberFormatException
            if (markIndex < 1 || markIndex > tasks.size()) {
                throw new MiloIceException("Invalid index: there are only " + tasks.size() + " task(s)");
            }
            Task task = tasks.getTask(markIndex - 1);
            if (!task.markAsDone()) {
                throw new MiloIceException("Task has already been marked before");
            }
            return new ToggleMarkCommand(inputEnum, markIndex);

        } else if (inputEnum == Enum.UNMARK) {
            if (parts.length != 2) {
                throw new MiloIceException("Invalid input, correct example: unmark 1");
            }
            int unmarkIndex = Integer.parseInt(parts[1]); // might throw NumberFormatException
            if (unmarkIndex < 1 || unmarkIndex > tasks.size()) {
                throw new MiloIceException("Invalid index: there are only " + tasks.size() + " task(s)");
            }
            Task task = tasks.getTask(unmarkIndex - 1);
            if (!task.unmarkAsDone()) {
                throw new MiloIceException("Task isn't marked yet");
            }
            return new ToggleMarkCommand(inputEnum, unmarkIndex);

        } else if (inputEnum == Enum.TODO) {
            int byIdx = input.indexOf("/by");
            int fromIdx = input.indexOf("/from");
            int toIdx = input.indexOf("/to");
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                throw new MiloIceException("Invalid input: description of todo cannot be empty");
            } else if (byIdx != -1 || fromIdx != -1 || toIdx != -1) {
                // don't allow /by /from /to in To_do
                throw new MiloIceException("Invalid input: Todo should not contain '/by', '/from', '/to'");
            } else {
                Todo todo = new Todo(description, false);
                return new AddCommand(todo);
            }
        } else if (inputEnum == Enum.DEADLINE) {
            int byIdx = input.indexOf(" /by ");
            if (byIdx != -1) {
                String description = input.substring(8, byIdx).trim();
                String deadline = input.substring(byIdx + 4).trim();
                if (description.isEmpty() || deadline.isEmpty()) {
                    throw new MiloIceException("Invalid input: description and deadline cannot be empty");
                }
                Deadline dl = Deadline.of(description, false, deadline);
                return new AddCommand(dl);
            } else {
                throw new MiloIceException(
                        "Please specify the deadline using '/by'\n"
                        + "Example: deadline finish cs2103t project /by [yyyy-MM-dd]");
            }
        } else if (inputEnum == Enum.EVENT) {
            int fromIdx = input.indexOf(" /from ");
            int toIdx = input.indexOf(" /to ");
            if (fromIdx != -1 && toIdx != -1) {
                String description = input.substring(5, fromIdx).trim();
                String start = input.substring(fromIdx + 6, toIdx).trim();
                String end = input.substring(toIdx + 4).trim();
                if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
                    throw new MiloIceException("Invalid input: description, start and end cannot be empty");
                }
                Event event = Event.of(description, false, start, end);
                return new AddCommand(event);
            } else {
                throw new MiloIceException(
                        "Please specify the start date using '/from' and end date using '/to'\n"
                        + "Example: event [description] /from [yyyy-MM-dd] /to [yyyy-MM-dd]");
            }
        } else if (inputEnum == Enum.FIND) {
            String keyword = input.substring(4).trim();
            return new FindCommand(keyword);
        } else {
            throw new MiloIceException("""
                    Sorry, I don't understand your request
                    Here's what I can do for you:
        
                    1. list: view all tasks
                    2. bye: Exit chatbot
                    3. mark: mark the task with the given index (mark 2)
                    4. unmark: opposite of mark (unmark 3)
                    5. todo: add todo task (todo finish cs2103t)
                    6. deadline: add a task with a deadline (deadline cs2103t /by [yyyy-MM-dd])
                    7. event: add a task with start & end time (event cs2103t /from [yyyy-MM-dd] /to [yyyy-MM-dd])""");
        }
    }
}
