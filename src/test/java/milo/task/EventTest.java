package milo.task;

import milo.MiloIceException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void event_factoryMethod_success() {
        String description = "des";
        boolean isDone = true;
        try {
            String stringStart = "2025-01-01";
            String stringEnd = "2025-01-02";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(stringStart, formatter);
            LocalDate end = LocalDate.parse(stringEnd, formatter);
            assertEquals(new Event(description, isDone, start, end),
                    Event.of(description, isDone, stringStart, stringEnd));
        } catch (Exception e) {
            // should not throw exception
            fail();
        }
    }

    @Test
    public void event_factoryMethod_invalidDate() {
        String description = "des";
        boolean isDone = true;
        try {
            String stringStart = "2025-1-1";
            String stringEnd = "2025-01-02";
            Event.of(description, isDone, stringStart, stringEnd);
            fail(); // should throw MiloIceException
        } catch (MiloIceException e) {
            assertEquals("Invalid time format: Should be [yyyy-MM-dd]", e.getMessage());
        }
    }

    @Test
    public void todoTextFormat() {
        String description = "des";
        boolean isDone = true;
        String stringStart = "2025-01-01";
        String stringEnd = "2025-01-02";
        try {
            assertEquals("D | 1 | des | 2025-01-01 | 2025-01-02",
                    Event.of(description, isDone, stringStart, stringEnd).toTextFormat());
        } catch (Exception e) {
            // should not throw exception
            fail();
        }
    }
}
