package avocado.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;    

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }   

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }   

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void markTaskAsNotDone(int index) {
        tasks.get(index).markAsNotDone();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index The index of the task to get.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Prints the list of tasks.
     */
    // public void printTaskList() {
    //     System.out.println("____________________________________________________________");
    //     if (tasks.isEmpty()) {
    //         System.out.println(" No tasks available!");
    //     } else {
    //         System.out.println(" Here are the tasks in your list:");
    //         for (int i = 0; i < tasks.size(); i++) {
    //             System.out.println(" " + (i + 1) + ". " + tasks.get(i));
    //         }
    //     }
    //     // System.out.println("____________________________________________________________");
    // }

    public String printTaskList() {
        if (tasks.isEmpty()) {
            return " No tasks available!";
        } else {
            StringBuilder taskList = new StringBuilder();
            taskList.append(" Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                taskList.append(" " + (i + 1) + ". " + tasks.get(i) + "\n");
            }
            return taskList.toString();
        }
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    // public void findTask(String keyword) {
    //     System.out.println("____________________________________________________________");
    //     System.out.println(" Here are the matching tasks in your list:");
    //     int count = 0;
    //     for (int i = 0; i < tasks.size(); i++) {
    //         if (tasks.get(i).getDescription().contains(keyword)) {
    //             System.out.println(" " + (i + 1) + ". " + tasks.get(i));
    //             count++;
    //         }
    //     }
    //     if (count == 0) {
    //         System.out.println(" No matching tasks found!");
    //     }
    //     // System.out.println("____________________________________________________________");
    // }

    public String findTask(String keyword) {
        StringBuilder matchingTasks = new StringBuilder();
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                matchingTasks.append(" " + (i + 1) + ". " + tasks.get(i) + "\n");
                count++;
            }
        }
        if (count == 0) {
            return " No matching tasks found!";
        } else {
            return " Here are the matching tasks in your list:\n" + matchingTasks.toString();
        }
    }
}
