import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/tasks.txt";

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Ensure directory exists
            FileWriter writer = new FileWriter(file);

            for (Task task : tasks) {
                writer.write(taskToFileFormat(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks; // Return empty list if no previous data
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found.");
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    private static String taskToFileFormat(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + (d.isDone ? "1" : "0") + " | " + d.description + " | " + d.getBy();
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + (e.isDone ? "1" : "0") + " | " + e.description + " | " + e.getFrom() + " | " + e.getTo();
        }
        return "";
    }

    private static Task parseTaskFromFile(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
                case "T":
                    Todo todo = new Todo(description);
                    if (isDone) todo.markAsDone();
                    return todo;
                case "D":
                    Deadline deadline = new Deadline(description, parts[3]);
                    if (isDone) deadline.markAsDone();
                    return deadline;
                case "E":
                    Event event = new Event(description, parts[3], parts[4]);
                    if (isDone) event.markAsDone();
                    return event;
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Skipping corrupted line: " + line);
            return null;
        }
    }
}
