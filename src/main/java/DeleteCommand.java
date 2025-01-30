public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        Task task = tasks.deleteTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showTaskDeleted(task, tasks);
    }
    
}
