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
