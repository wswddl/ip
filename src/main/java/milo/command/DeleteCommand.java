package milo.command;

import milo.Storage;
import milo.Ui;
import milo.task.Task;
import milo.task.TaskList;

public class DeleteCommand extends Command {

    private int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task removedTask = tasks.removeTask(deleteIndex - 1); ///// remember to change when using TaskList
        ui.deleteTaskUi(removedTask, tasks);
        storage.updateTask(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
