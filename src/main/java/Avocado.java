import java.util.Scanner;

public class Avocado {
    private static final int MAX_TASKS = 100; // Limit 
    private static Task[] tasks = new Task[MAX_TASKS]; 
    private static int taskCount = 0; 
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
            } else {
                addTask(input);
            }
        }
        scanner.close();
    }

    private static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Task(taskDescription);
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" added: " + taskDescription);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Sorry, you have reached the task limit!");
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
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskIndex]);
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Task number out of range.");
                System.out.println("____________________________________________________________");
            }
        } catch (Exception e) {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid command. Use: mark <task number>");
            System.out.println("____________________________________________________________");
        }
    }

    private static void unmarkTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[taskIndex]);
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Task number out of range.");
                System.out.println("____________________________________________________________");
            }
        } catch (Exception e) {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid command. Use: unmark <task number>");
            System.out.println("____________________________________________________________");
        }
    }
}
