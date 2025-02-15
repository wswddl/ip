package milo.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * The TaskList class provides methods to add, remove, and retrieve tasks
 * from the list, as well as to check the size of the list.
 */
public class TaskList {
    private final ArrayList<Task> listOfTask;

    public TaskList() {
        this.listOfTask = new ArrayList<>();
    }

    /**
     * Returns the size of the task list.
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return this.listOfTask.size();
    }

    /**
     * Returns an ArrayList object.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getList() {
        return listOfTask;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        listOfTask.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     * The index is adjusted to account for zero-based indexing.
     *
     * @param index the index of the task to be removed
     * @return the removed task
     */
    public Task removeTask(int index) {
        assert index >= 0 && index < listOfTask.size() : "index out of bound";
        return listOfTask.remove(index);
    }

    /**
     * Retrieves a task from the task list at the specified index.
     * The index is adjusted to account for zero-based indexing.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        assert index >= 0 && index < listOfTask.size() : "index out of bound";
        return listOfTask.get(index);
    }

    /**
     * Searches through the list of tasks to find all tasks that contain the specified keyword (ignore casing)
     * in their description.
     * Iterates through each task and checks if the task's description contains
     * the given keyword. If a match is found, the task is added to the result list.
     *
     * @param keyword The keyword to search for in the descriptions of the tasks.
     * @return An ArrayList containing all tasks whose descriptions contain the specified keyword.
     */
    public ArrayList<Task> findKeyword(String keyword) {
        assert keyword != null : "find keyword cannot be null";
        ArrayList<Task> arr = new ArrayList<>();
        for (Task task : listOfTask) {
            String taskDescription = task.getDescription().toUpperCase();
            keyword = keyword.toUpperCase();
            int keywordIdx = taskDescription.indexOf(keyword);
            if (keywordIdx != -1) {
                arr.add(task);
            }
        }
        return arr;
    }
}
