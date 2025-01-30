package avocado;

import avocado.command.Command;
import avocado.parser.Parser;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;

public class Avocado {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Avocado(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasks());
    }

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

    public static void main(String[] args) {
        new Avocado("data/tasks.txt").run();
    }
}



