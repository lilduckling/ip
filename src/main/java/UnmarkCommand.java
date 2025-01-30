public class UnmarkCommand extends Command {
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        tasks.markTaskAsNotDone(taskNumber);
        storage.saveTasks(tasks.getTasks());
        ui.showMarkedAsUndone(tasks.getTask(taskNumber));
    }
    
}
