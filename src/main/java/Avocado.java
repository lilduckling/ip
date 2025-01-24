import java.util.Scanner;

public class Avocado {
    private static final int MAX_TASKS = 100; // Limit of tasks
    private static Task[] tasks = new Task[MAX_TASKS]; // Storage for tasks
    private static int taskCount = 0; // Track the number of tasks

    public static void main(String[] args) {
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

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printTaskList();
            } else if (input.startsWith("mark ")) {
                markTaskAsDone(input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
            } else if (input.startsWith("todo ")) {
                addTodo(input.substring(5));
            } else if (input.startsWith("deadline ")) {
                addDeadline(input.substring(9));
            } else if (input.startsWith("event ")) {
                addEvent(input.substring(6));
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" I'm sorry, I don't recognize that command.");
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }

    private static void addTodo(String description) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Todo(description);
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks[taskCount - 1]);
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }
    }

    private static void addDeadline(String input) {
        String[] parts = input.split(" /by ", 2);
        if (parts.length < 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid format! Use: deadline <task> /by <date>");
            System.out.println("____________________________________________________________");
            return;
        }
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Deadline(parts[0], parts[1]);
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks[taskCount - 1]);
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }
    }

    private static void addEvent(String input) {
        String[] parts = input.split(" /from | /to ", 3);
        if (parts.length < 3) {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid format! Use: event <task> /from <start> /to <end>");
            System.out.println("____________________________________________________________");
            return;
        }
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks[taskCount - 1]);
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }
    }

    private static void printTaskList() {
        System.out.println("____________________________________________________________");
        if (taskCount == 0) {
            System.out.println(" No tasks available!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTaskAsDone(String input) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            tasks[taskIndex].markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks[taskIndex]);
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            System.out.println(" Invalid task number.");
        }
    }

    private static void unmarkTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            tasks[taskIndex].markAsNotDone();
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks[taskIndex]);
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            System.out.println(" Invalid task number.");
        }
    }
}
 