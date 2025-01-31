package milo.command;

import milo.Storage;
import milo.Ui;
import milo.task.Task;
import milo.task.TaskList;

public class AddCommand extends Command {

    private Task addedTask;
    public AddCommand(Task addedTask) {
        this.addedTask = addedTask;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.addedTask);
        ui.addTaskUi(this.addedTask, tasks);
        storage.updateTask(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
