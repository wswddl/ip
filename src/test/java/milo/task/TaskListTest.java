package milo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
        tasks.addTask(new Todo("Be Happy", false)); // :(
        assertEquals(1, tasks.size());
    }

    @Test
    public void removeTaskTest() {
        TaskList tasks = new TaskList();
        Task task1 = new Todo("Complete ip", false);
        tasks.addTask(task1);
        assertEquals(1, tasks.size());
        Task task2 = tasks.removeTask(1);
        assertEquals(0, tasks.size());
        assertEquals(task1, task2);
    }

    @Test
    public void getTaskTest() {
        TaskList tasks = new TaskList();
        Task task1 = new Todo("Touch grass", true);
        Task task2 = new Todo("Touch grass", true);
        tasks.addTask(task1);
        tasks.addTask(task2);
        assertEquals(2, tasks.size());
        assertEquals(task2, tasks.getTask(1));
        assertEquals(2, tasks.size());
    }

}

