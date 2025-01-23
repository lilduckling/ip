import java.util.Scanner;

public class Avocado {
    private static final int MAX_TASKS = 100; // Limit 
    private static String[] tasks = new String[MAX_TASKS]; 
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
            } else {
                addTask(input);
            }
        }
        scanner.close();
    }

    private static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" added: " + task);
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
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println("____________________________________________________________");
    }
}
