package milo.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public abstract String toTextFormat();
    /*
     * set isDone to true
     *
     * @return true if isDone was false, false if isDone was true
     */
    public boolean markAsDone() {
        if (this.isDone) {
            return false;
        }
        this.isDone = true;
        return true;
    }

    /*
     * set isDone to false
     *
     * @return true if isDone was true, false if isDone was false
     */
    public boolean unmarkAsDone() {
        if (!this.isDone) {
            return false;
        }
        this.isDone = false;
        return true;
    }

    /*
     * get the isDone status of the task in String format
     *
     * @return "X" if isDone is true, " " if isDone is false
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
