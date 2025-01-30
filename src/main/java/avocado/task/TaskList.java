package avocado.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;    

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }   

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }   

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void markTaskAsNotDone(int index) {
        tasks.get(index).markAsNotDone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }


    public void printTaskList() {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println(" No tasks available!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
        // System.out.println("____________________________________________________________");
    }
}
