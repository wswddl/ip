package milo;

import milo.command.Command;
import milo.task.TaskList;

/**
 * The main application class for MiloIce. This class is responsible for
 * managing user interactions, and handling commands to modify tasks in the task list.
 * It interacts with the UI, storage, and task list.
 */
public class MiloIce {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
     * Starts the MiloIce application and enters the main application loop.
     * This method continually prompts the user for input, parses the input into commands,
     * executes the commands, and updates the UI and storage until the user exits the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MiloIceException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Input must be a number (Eg. mark 1, unmark 1, delete 3)");
            }
        }
    }

    public static void main(String[] args) {
        new MiloIce("./src/main/java/data/savedTasks.txt").run();
    }
}

