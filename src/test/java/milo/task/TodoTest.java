package milo.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void todo_factoryMethod_success() {
        String description = "des";
        boolean isDone = true;
        try {
            assertEquals(new Todo(description, isDone), Todo.of(description, isDone));
        } catch (Exception e) {
            // Todo_ should not throw exception in runtime
            fail();
        }
    }

    @Test
    public void todoTextFormat() {
        String description = "des";
        boolean isDone = true;
        try {
            assertEquals("T | 1 | des", Todo.of(description, isDone).toTextFormat());
        } catch (Exception e) {
            // Todo_ should not throw exception in runtime
            fail();
        }
    }
}
