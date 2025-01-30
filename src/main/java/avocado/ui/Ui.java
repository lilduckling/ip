package avocado.ui;

import avocado.task.Task;
import avocado.task.TaskList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = "     _             \n"
                + "    / \\__    ____ \n"
                + "   / _ \\ \\  / /  \\ \n"
                + "  / ___ \\ \\/ / |  |\n"
                + " /_/   \\_\\__/\\___/ \n";

        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Avocado");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }       

    public void showError(String message) {
        System.out.println(" " + message);
    }

    public void showMarkedAsDone(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        // System.out.println("____________________________________________________________");
    }

    public void showMarkedAsUndone(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as not done:");
        System.out.println("   " + task);
        // System.out.println("____________________________________________________________");
    }

    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.getTasks().size() + " tasks in the list.");
        // System.out.println("____________________________________________________________");
    }

    public void showTaskDeleted(Task task, TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.getTasks().size() + " tasks in the list.");
        // System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        // System.out.println("____________________________________________________________");
    }
}
