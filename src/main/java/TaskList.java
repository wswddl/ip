import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> listOfTask;

    public TaskList() {
        this.listOfTask = new ArrayList<>();
    }

    public int size() {
        return this.listOfTask.size();
    }

    public ArrayList<Task> getList() {
        return listOfTask;
    }

    public void addTask(Task task) {
        listOfTask.add(task);
    }

    public Task removeTask(int index) {
        return listOfTask.remove(index - 1);
    }

    public Task getTask(int index) {
        return listOfTask.get(index);
    }





}
