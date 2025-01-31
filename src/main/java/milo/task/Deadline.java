package milo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import milo.MiloIceException;

public class Deadline extends Task {

    protected LocalDate deadline;
    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public static Deadline of(String description, boolean isDone, String stringDeadline) throws MiloIceException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadline = LocalDate.parse(stringDeadline, formatter);
            return new Deadline(description, isDone, deadline);
        } catch (DateTimeParseException e) {
            throw new MiloIceException("Invalid time format: Should be [yyyy-MM-dd]");
        }
    }

    @Override
    public String toTextFormat() {
        String stringDeadline = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + stringDeadline;
    }

    @Override
    public String toString() {
        String stringDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " + stringDeadline + ")";
    }
}
