package milo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import milo.MiloIceException;

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
    @Test void findKeywordTest() {
        try {
            String des1 = "book hotel";
            Task task1 = Todo.of(des1, false);
            String des2 = "rebook hotel";
            Task task2 = Deadline.of(des2, false, "2020-01-01");
            String des3 = "BooK Hotel";
            Task task3 = new Todo(des3, false);
            String des4 = "Be Happy";
            Task task4 = new Todo(des4, false);

            TaskList tasks = new TaskList();
            tasks.addTask(task1);
            tasks.addTask(task2);
            tasks.addTask(task3);
            tasks.addTask(task4);

            ArrayList<Task> arrTest = new ArrayList<>();
            arrTest.add(task1);
            arrTest.add(task2);
            arrTest.add(task3);
            assertEquals(arrTest, tasks.findKeyword("boOk"), "ignore casing of the description");
        } catch (MiloIceException e) {
            // shouldn't throw exception
            fail();
        }
    }

}

