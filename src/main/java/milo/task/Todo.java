package milo.task;

import milo.MiloIceException;

/**
 * Represents a task without any specific date.
 * A Todo has description and completion status.
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class
     * Initializes the task with the specified description and completion status.
     *
     * @param description The description of the Todo task.
     * @param isDone      The completion status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Factory method to create a new Todo task with the given description and completion status.
     *
     * @param description The description of the Todo task.
     * @param isDone The completion status of the Todo task.
     * @return A new Todo task instance.
     * @throws MiloIceException If an error occurs during creation, such as invalid input.
     */
    public static Todo of(String description, boolean isDone) throws MiloIceException {
        assert description != null : "Description cannot be null";
        return new Todo(description, isDone);
    }

    /**
     * Returns the text format for saving to a text file storage.
     * The format is "T | <isDone> | <description>" where <isDone> is 1 if completed and 0 if not.
     *
     * @return A string representing the Todo task in a text format.
     */
    @Override
    public String toTextFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the Todo task.
     * The format is "[T][<statusIcon>] <description>", where statusIcon is
     * "X" for completed tasks and " " for incomplete tasks.
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Compares this Todo task with another object for equality.
     * Two Todo tasks are considered equal if they have the same description and completion status.
     *
     * @param obj The object to compare with this Todo task.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Todo) {
            Todo todo = (Todo) obj;
            return this.description.equals(todo.description) && this.isDone == todo.isDone;
        } else {
            return false;
        }
    }

}
