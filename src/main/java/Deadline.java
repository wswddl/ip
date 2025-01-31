public class Deadline extends Task {

    protected String deadline;
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toTextFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " + this.deadline + ")";
    }
}
