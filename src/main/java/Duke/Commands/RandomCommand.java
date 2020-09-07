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
     * Assigns string to a value of string
     *
     * @param string assigns string to this this.string
     */
    public RandomCommand(String string) {
        super(string);
    }

    /**
     * Gives wrong input exception
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return String returns the string of the output that informs the action has been complete.
     * @throws WrongInputException is thrown
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WrongInputException {
        throw new WrongInputException();
    }
}

