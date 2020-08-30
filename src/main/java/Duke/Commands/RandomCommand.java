package Duke.Commands;

import Duke.Errors.WrongInputException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

/**
 * handles case where a random word is being input
 */
public class RandomCommand extends Command {
    /**
     * assigns string to a value of string
     * @param string assigns string to this this.string
     */
    public RandomCommand(String string) {
        super(string);
    }

    /**
     * gives wrong input exception
     * @param tasks
     * @param ui
     * @param storage
     * @throws WrongInputException is thrown
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WrongInputException {
        throw new WrongInputException();
    }
}
