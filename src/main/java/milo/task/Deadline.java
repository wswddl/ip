package milo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import milo.MiloIceException;

/**
 * Represents a task with a deadline.
 * A Deadline has description, completion status, and specific deadline date.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * Constructor for a Deadline task.
     *
     * @param description the description of the task
     * @param isDone the completion status of the task
     * @param deadline the deadline date of the task
     */
    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone);
        assert deadline != null : "Deadline cannot be null";
        this.deadline = deadline;
    }

    /**
     * Factory method to create a new Deadline task from a string representing the deadline.
     * The date string should follow the format "yyyy-MM-dd".
     *
     * @param description the description of the task
     * @param isDone the completion status of the task
     * @param stringDeadline the deadline date as a string in "yyyy-MM-dd" format
     * @return a new Deadline task
     * @throws MiloIceException if the date format is invalid
     */
    public static Deadline of(String description, boolean isDone, String stringDeadline) throws MiloIceException {
        assert description != null : "Description cannot be null";
        assert stringDeadline != null : "Deadline input cannot be null";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime deadline = LocalDateTime.parse(stringDeadline, formatter);
            return new Deadline(description, isDone, deadline);
        } catch (DateTimeParseException e) {
            throw new MiloIceException("Invalid time format: Should be [yyyy-MM-dd HHmm]");
        }
    }

    /**
     * Returns the text format for saving to a text file storage.
     * The format is "D | <isDone> | <description> | <deadline>" where isDone is either 1 (done) or 0 (not done).
     *
     * @return A string representing the Deadline task in a text format.
     */
    @Override
    public String toTextFormat() {
        // deadline string should have the same format as the user input format
        String stringDeadline = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + stringDeadline;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The format is "[D][<statusIcon>] <description> (by: <deadline>)", where statusIcon is
     * "X" for completed tasks and " " for incomplete tasks.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        String stringDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " + stringDeadline + ")";
    }

    /**
     * Compares this Deadline task with another object for equality.
     * Two Deadline tasks are considered equal if they have the same
     * description, completion status and deadline.
     *
     * @param obj The object to compare with this Deadline task.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Deadline) {
            Deadline dl = (Deadline) obj;
            return this.description.equals(dl.description) && this.isDone == dl.isDone
                    && this.deadline.equals(dl.deadline);
        } else {
            return false;
        }
    }
}
