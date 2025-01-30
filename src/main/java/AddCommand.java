public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        ui.showTaskAdded(task, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
