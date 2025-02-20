package milo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import milo.MiloIceException;

public class EventTest {
    @Test
    public void event_factoryMethod_success() {
        String description = "des";
        boolean isDone = true;
        try {
            String stringStart = "2025-01-01 1200";
            String stringEnd = "2025-01-02 1300";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime start = LocalDateTime.parse(stringStart, formatter);
            LocalDateTime end = LocalDateTime.parse(stringEnd, formatter);
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
            String stringStart = "2025-1-1 1200"; // error date format
            String stringEnd = "2025-01-02 1201";
            Event.of(description, isDone, stringStart, stringEnd);
            fail(); // should throw MiloIceException
        } catch (MiloIceException e) {
            assertEquals("Invalid time format: Should be [yyyy-MM-dd HHmm]\n"
                    + "Example: 2031-01-01 0100", e.getMessage());
        }

        try {
            String stringStart = "2025-01-01 201"; // error HHmm
            String stringEnd = "2025-01-02 0201";
            Event.of(description, isDone, stringStart, stringEnd);
            fail(); // should throw MiloIceException
        } catch (MiloIceException e) {
            assertEquals("Invalid time format: Should be [yyyy-MM-dd HHmm]\n"
                    + "Example: 2031-01-01 0100", e.getMessage());
        }
    }

    @Test
    public void todoTextFormat() {
        String description = "des";
        boolean isDone = true;
        String stringStart = "2025-01-01 0200";
        String stringEnd = "2025-01-02 0201";
        try {
            assertEquals("E | 1 | des | 2025-01-01 0200 | 2025-01-02 0201",
                    Event.of(description, isDone, stringStart, stringEnd).toTextFormat());
        } catch (Exception e) {
            // should not throw exception
            fail();
        }
    }
}
