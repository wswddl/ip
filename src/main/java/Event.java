public class Event extends Task {

    protected String start;
    protected String end;
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toTextFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description +
                " (from: " + this.start + " to: " + this.end + ")";
    }
}
