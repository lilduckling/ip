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
            try {
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
                    addTodo(input.substring(5).trim());
                } else if (input.startsWith("deadline ")) {
                    addDeadline(input.substring(9).trim());
                } else if (input.startsWith("event ")) {
                    addEvent(input.substring(6).trim());
                } else {
                    throw new AvocadoException("Oops! I don't understand the command: " + input);
                }
            } catch (AvocadoException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }

    private static void addTodo(String description) throws AvocadoException {
        if (description.isEmpty()) {
            throw new AvocadoException("Oops! The description of a todo cannot be empty.");
        }
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Todo(description);
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks[taskCount - 1]);
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            throw new AvocadoException("Oops! Task list is full. Cannot add more tasks.");
        }
    }

    private static void addDeadline(String input) throws AvocadoException {
        String[] parts = input.split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new AvocadoException("Oops! Deadline format should be: deadline <task> /by <date>");
        }
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Deadline(parts[0], parts[1]);
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks[taskCount - 1]);
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            throw new AvocadoException("Oops! Task list is full. Cannot add more tasks.");
        }
    }

    private static void addEvent(String input) throws AvocadoException {
        String[] parts = input.split(" /from | /to ", 3);
        if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new AvocadoException("Oops! Event format should be: event <task> /from <start> /to <end>");
        }
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks[taskCount - 1]);
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            throw new AvocadoException("Oops! Task list is full. Cannot add more tasks.");
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

    private static void markTaskAsDone(String input) throws AvocadoException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskCount) {
                throw new AvocadoException("Oops! Task number out of range.");
            }
            tasks[taskIndex].markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks[taskIndex]);
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            throw new AvocadoException("Oops! Please provide a valid task number to mark as done.");
        }
    }

    private static void unmarkTask(String input) throws AvocadoException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskCount) {
                throw new AvocadoException("Oops! Task number out of range.");
            }
            tasks[taskIndex].markAsNotDone();
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks[taskIndex]);
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            throw new AvocadoException("Oops! Please provide a valid task number to unmark.");
        }
    }
}
