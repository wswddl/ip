package milo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import milo.MiloIceException;

public class DeadlineTest {
    @Test
    public void deadline_factoryMethod_success() {
        String description = "des";
        boolean isDone = true;
        try {
            String stringDeadline = "2025-01-01";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadline = LocalDate.parse(stringDeadline, formatter);
            assertEquals(new Deadline(description, isDone, deadline), Deadline.of(description, isDone, stringDeadline));
        } catch (Exception e) {
            // should not throw exception
            fail();
        }
    }

    @Test
    public void deadline_factoryMethod_invalidDate() {
        String description = "des";
        boolean isDone = true;
        try {
            String stringDeadline = "2025-1-1";
            Deadline.of(description, isDone, stringDeadline);
            fail(); // should throw MiloIceException
        } catch (MiloIceException e) {
            assertEquals("Invalid time format: Should be [yyyy-MM-dd]", e.getMessage());
        }
    }

    @Test
    public void todoTextFormat() {
        String description = "des";
        boolean isDone = true;
        String stringDeadline = "2025-01-01";
        try {
            assertEquals("D | 1 | des | 2025-01-01",
                    Deadline.of(description, isDone, stringDeadline).toTextFormat());
        } catch (Exception e) {
            // should not throw exception
            fail();
        }
    }
}
