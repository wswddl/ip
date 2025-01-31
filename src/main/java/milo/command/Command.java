package milo.command;

import milo.Storage;
import milo.Ui;
import milo.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
