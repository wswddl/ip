package milo.task;

import milo.MiloIceException;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public static Todo of(String description, boolean isDone) throws MiloIceException {
        return new Todo(description, isDone);
    }

    @Override
    public String toTextFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
}
