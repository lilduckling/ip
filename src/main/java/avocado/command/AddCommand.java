package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.Task;
import avocado.task.TaskList;
import avocado.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        ui.showTaskAdded(task, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
