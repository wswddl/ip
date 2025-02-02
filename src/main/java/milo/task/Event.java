package milo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import milo.MiloIceException;

/**
 * Represents a task with a start and end date.
 * An Event has description, completion status, start and end date.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor for an Event task.
     *
     * @param description the description of the event
     * @param isDone the completion status of the event
     * @param start the start date of the event
     * @param end the end date of the event
     */
    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Factory method to create a new Event task from string representations of the start and end dates.
     * The date strings should follow the format "yyyy-MM-dd".
     *
     * @param description the description of the event
     * @param isDone the completion status of the event
     * @param stringStart the start date as a string in "yyyy-MM-dd" format
     * @param stringEnd the end date as a string in "yyyy-MM-dd" format
     * @return a new Event task
     * @throws MiloIceException if the date format is invalid
     */
    public static Event of(String description, boolean isDone,
                           String stringStart, String stringEnd) throws MiloIceException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime start = LocalDateTime.parse(stringStart, formatter);
            LocalDateTime end = LocalDateTime.parse(stringEnd, formatter);
            return new Event(description, isDone, start, end);

        } catch (DateTimeParseException e) {
            throw new MiloIceException("Invalid time format: Should be [yyyy-MM-dd HHmm]");
        }
    }

    /**
     * Returns the text format for saving to a text file storage.
     * The format is "D | <isDone> | <description> | <start> | <end>" where isDone is either 1 (done) or 0 (not done).
     *
     * @return A string representing the Event task in a text format.
     */
    @Override
    public String toTextFormat() {
        String stringStart = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        String stringEnd = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + stringStart + " | " + stringEnd;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The format is "[D][<statusIcon>] <description> (from: <start> to: <end>)", where statusIcon is
     * "X" for completed tasks and " " for incomplete tasks.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        String stringStart = start.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        String stringEnd = end.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return "[E][" + this.getStatusIcon() + "] " + this.description
                + " (from: " + stringStart + " to: " + stringEnd + ")";
    }

    /**
     * Compares this Event task with another object for equality.
     * Two Event tasks are considered equal if they have the same
     * description, completion status, start and end dates.
     *
     * @param obj The object to compare with this Event task.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Event) {
            Event event = (Event) obj;
            return this.description.equals(event.description) && this.isDone == event.isDone
                    && this.start.equals(event.start) && this.end.equals(event.end);
        } else {
            return false;
        }
    }
}
