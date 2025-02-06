package avocado;

import avocado.command.Command;
import avocado.parser.Parser;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;

/**
 * The main class for the Avocado chatbot.
 * Handles initialization and execution of user commands.
 */

public class Avocado {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs an Avocado instance with a specified storage file.
     *
     * @param filePath The path to the file used for saving/loading tasks.
     */

    public Avocado(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Runs the Avocado chatbot.
     * Displays welcome message and reads user commands until the user exits the chatbot.
     */

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String showWelcome() {
        return ui.showWelcome();
    }
    

    /**
     * The main method for the Avocado chatbot.
     * Creates an Avocado instance and runs the chatbot.
     *
     * @param args The command line arguments.
     */

    public static void main(String[] args) {
        new Avocado("data/tasks.txt").run();
    }
}



