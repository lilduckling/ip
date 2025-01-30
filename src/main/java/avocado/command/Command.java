package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException;

    public boolean isExit() {
        return false;
    }
}
