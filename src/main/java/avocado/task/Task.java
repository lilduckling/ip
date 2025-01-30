package avocado.task;

/**
 * Represents a task in the task list.
 */
public class Task {
    protected  String description;
    protected  boolean isDone;

    /**
     * Constructor for Task.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the status of the task.
     * @return The status of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the status icon of the task.
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}





