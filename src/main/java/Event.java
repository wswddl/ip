public class Event extends Task {

    protected String start;
    protected String end;
    protected String deadline;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description +
                " (from: " + this.start + " to: " + this.end + ")";
    }
}
