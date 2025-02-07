package milo;

import milo.command.Command;
import milo.task.TaskList;

/**
 * The main application class for MiloIce. This class is responsible for
 * managing user interactions, and handling commands to modify tasks in the task list.
 * It interacts with the UI, storage, and task list.
 */
public class MiloIce {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private String commandType = "";

    /**
     * Constructor for the MiloIce application.
     * Initializes the UI, storage, and task list. It also loads any saved tasks
     * from the specified file path into the task list.
     *
     * @param filePath the path to the file where tasks are saved
     */
    public MiloIce(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        tasks = new TaskList();
        storage.loadTask(tasks);
    }

    /**
     * Given the user input, output the bot's response.
     *
     * @param input the user's input in the text field.
     * @return the bot's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input, tasks);
            c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            return c.getCommandResponse();
        } catch (MiloIceException e) {
            System.out.println(e.getMessage()); // keep this for future debugging
            return e.getMessage();
        } catch (NumberFormatException e) {
            System.out.println("Input must be a number (Eg. mark 1, unmark 1, delete 3)"); // keep this for future debugging
            return "Input must be a number (Eg. mark 1, unmark 1, delete 3)";
        }
    }

    public String getCommandType() {
        return commandType;
    }

}

