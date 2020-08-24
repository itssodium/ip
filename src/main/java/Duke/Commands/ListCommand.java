package Duke.Commands;
import Duke.Errors.DukeException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.Task;

public class ListCommand extends Command {
    public ListCommand(String string) {
        super(string);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);
        for(int i = 0; i < tasks.getAllTasks().size(); i++){
            System.out.println(tasks.getAllTasks().get(i));
        }
    }

}
