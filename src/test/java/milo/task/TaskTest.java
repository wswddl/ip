package milo.task;

import milo.MiloIceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void markTest() {
        try {
            Task task = Todo.of("des", false);
            assertTrue(task.markAsDone());
            assertTrue(task.isDone);
            assertFalse(task.markAsDone());
        } catch (MiloIceException e) {
            // should not throw exception
            fail();
        }
    }

    @Test
    public void unmarkTest() {
        try {
            Task task = Todo.of("des", true);
            assertTrue(task.unmarkAsDone());
            assertFalse(task.isDone);
            assertFalse(task.unmarkAsDone());
        } catch (MiloIceException e) {
            // should not throw exception
            fail();
        }
    }

}
