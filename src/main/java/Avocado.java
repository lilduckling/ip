import java.util.ArrayList;
import java.util.Scanner;


public class Avocado {
    private static final ArrayList<Task> tasks = new ArrayList<>(); 

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
                } else if (input.startsWith("delete ")) {
                    deleteTask(input);
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
        tasks.add(new Todo(description));
        Storage.saveTasks(tasks);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.get(tasks.size() - 1));
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void addDeadline(String input) throws AvocadoException {
        String[] parts = input.split(" /by ", 2);
        if (parts.length < 2) {
            throw new AvocadoException("Oops! Deadline format should be: deadline <task> /by yyyy-MM-dd");
        }
        try {
            tasks.add(new Deadline(parts[0], parts[1]));
            Storage.saveTasks(tasks);
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks.get(tasks.size() - 1));
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            throw new AvocadoException("Oops! Invalid date format. Deadline should be in yyyy-MM-dd format.");
        }
    }

    private static void addEvent(String input) throws AvocadoException {
        String[] parts = input.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new AvocadoException("Oops! Event format should be: event <task> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        try {
            tasks.add(new Event(parts[0], parts[1], parts[2]));
            Storage.saveTasks(tasks);
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks.get(tasks.size() - 1));
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            throw new AvocadoException("Oops! Invalid date format. Event dates should be in yyyy-MM-dd HHmm format.");
        }
    }

    private static void deleteTask(String input) throws AvocadoException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new AvocadoException("Oops! Task number out of range.");
            }
            Task removedTask = tasks.remove(taskIndex);
            Storage.saveTasks(tasks);
            System.out.println("____________________________________________________________");
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            throw new AvocadoException("Oops! Please provide a valid task number to delete.");
        }
    }

    private static void printTaskList() {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println(" No tasks available!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTaskAsDone(String input) throws AvocadoException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new AvocadoException("Oops! Task number out of range.");
            }
            tasks.get(taskIndex).markAsDone();
            Storage.saveTasks(tasks);
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskIndex));
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            throw new AvocadoException("Oops! Please provide a valid task number to mark as done.");
        }
    }

    private static void unmarkTask(String input) throws AvocadoException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new AvocadoException("Oops! Task number out of range.");
            }
            tasks.get(taskIndex).markAsNotDone();
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(taskIndex));
            System.out.println("____________________________________________________________");
        } catch (Exception e) {
            throw new AvocadoException("Oops! Please provide a valid task number to unmark.");
        }
    }
}
