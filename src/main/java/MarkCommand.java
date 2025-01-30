public class MarkCommand extends Command {
    private final int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        tasks.markTaskAsDone(taskNumber);
        storage.saveTasks(tasks.getTasks());
        ui.showMarkedAsDone(tasks.getTasks().get(taskNumber));
    }
}
