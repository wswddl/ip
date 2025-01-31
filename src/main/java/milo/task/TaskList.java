package milo.task;

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

    /**
     * Searches through the list of tasks to find all tasks that contain the specified keyword
     * in their description.
     * Iterates through each task and checks if the task's description contains
     * the given keyword. If a match is found, the task is added to the result list.
     *
     * @param keyword The keyword to search for in the descriptions of the tasks.
     * @return An ArrayList containing all tasks whose descriptions contain the specified keyword.
     */
    public ArrayList<Task> findKeyword(String keyword) {
        ArrayList<Task> arr = new ArrayList<>();
        for (Task task : listOfTask) {
            int keywordIdx = task.getDescription().indexOf(keyword);
            if (keywordIdx != -1) {
                arr.add(task);
            }
        }
        return arr;
    }





}
