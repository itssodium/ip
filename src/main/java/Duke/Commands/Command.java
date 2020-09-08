package Duke.Commands;

import Duke.Errors.DukeException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

/**
 * this is an abstract class used for polymorphism
 */
abstract public class Command {
    /**
     * commandName which contains information on task and details to perform task
     * isExit is used to tell whether program terminates, where id true, it terminates
     */
    protected String commandDescription;
    protected int lengthOfKeyword;
    boolean isExit = false;

    /**
     * Assigns string to a value
     *
     * @param commandDescription assigns this.string to string
     */
    public Command(String commandDescription, int lengthOfKeyword){
        this.commandDescription = commandDescription;
        this.lengthOfKeyword = lengthOfKeyword;
    }

    /**
     * gets value of exit
     * @return exit
     */
    public boolean isExit(){
        return this.isExit;
    }

    /**
     * Executes the necessary task
     *
     * @param tasks used to access tasks in its list and change if necessary
     * @param ui
     * @param storage to change the input there if necessary
     * @throws DukeException if there are exceptions present
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the number/description is present.
     *
     * @return true is the number/description is absent and false if number is present.
     */
    protected boolean isNumberOrDescriptionAbsent(){
        return commandDescription.length() == lengthOfKeyword || commandDescription.length() == lengthOfKeyword + 1; //since the delete number appears after length of keyword/+1
    }
}
