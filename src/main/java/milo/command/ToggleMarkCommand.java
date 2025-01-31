package milo.command;

import milo.Storage;
import milo.Ui;
import milo.Enum;
import milo.task.Task;
import milo.task.TaskList;


public class ToggleMarkCommand extends Command {
    private int toggleMarkIndex;
    private Enum taskEnum;

    public ToggleMarkCommand(Enum taskEnum, int toggleMarkIndex) {
        this.taskEnum = taskEnum;
        this.toggleMarkIndex = toggleMarkIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskEnum == Enum.MARK) {
            Task markTask = tasks.getTask(toggleMarkIndex);
            ui.markTaskUi(markTask);
        } else if (taskEnum == Enum.UNMARK) {
            Task unmarkTask = tasks.getTask(toggleMarkIndex);
            ui.unmarkTaskUi(unmarkTask);
        }
        storage.updateTask(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
