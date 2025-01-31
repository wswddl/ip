package milo;

import milo.command.Command;
import milo.task.TaskList;

public class MiloIce {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public MiloIce(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        tasks = new TaskList();
        storage.loadTask(tasks);
    }

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
        new MiloIce("./data/savedTasks.txt").run();
    }
}

