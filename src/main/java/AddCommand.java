public class AddCommand extends Command {

    private Enum taskEnum;
    private Task addedTask;
    public AddCommand(Enum taskEnum, Task addedTask) {
        this.taskEnum = taskEnum;
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
