public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public boolean markAsDone() {
        if (this.isDone) {
            return false;
        }
        this.isDone = true;
        return true;
    }

    public boolean unMarkAsDone() {
        if (!this.isDone) {
            return false;
        }
        this.isDone = false;
        return true;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
