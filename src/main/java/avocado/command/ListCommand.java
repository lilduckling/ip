package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        tasks.printTaskList();
    }
}
