package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;

public class RemoveTagCommand extends Command {
    private final int taskIndex;
    private final String tag;

    public RemoveTagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        String result = tasks.untagTask(taskIndex, tag);
        storage.saveTasks(tasks.getTasks());
        return result;
    }
}
