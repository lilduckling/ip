public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        ui.showError("Oops! I don't understand this command.");
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
