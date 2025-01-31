import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;
    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public static Event of(String description, boolean isDone,
                           String stringStart, String stringEnd) throws  MiloIceException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(stringStart, formatter);
            LocalDate end = LocalDate.parse(stringEnd, formatter);
            return new Event(description, isDone, start, end);

        } catch (DateTimeParseException e) {
            throw new MiloIceException("Invalid time format: Should be [yyyy-MM-dd] from event");
        }
    }

    @Override
    public String toTextFormat() {
        String stringStart = start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String stringEnd = end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + stringStart + " | " + stringEnd;
    }

    @Override
    public String toString() {
        String stringStart = start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String stringEnd = end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E][" + this.getStatusIcon() + "] " + this.description +
                " (from: " + stringStart + " to: " + stringEnd + ")";
    }
}
