package Duke.Commands;

import Duke.Errors.DeleteException;
import Duke.Errors.DukeException;
import Duke.Errors.FileAbsentException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

import java.io.FileWriter;
import java.io.IOException;

/**
 * handles the case when the keyword is delete
 */
public class DeleteCommand extends Command {
    /**
     * assigns string to a value of string
     * @param string assigns string to this this.string
     */
    public DeleteCommand(String string) {
        super(string);
    }

    /**
     * Returns the String informing that the task is deleted
     *
     * @param tasks uses to give the current number of tasks
     * @param ID uses to get the task to be deleted.
     * @return String informing that the task is deleted.
     */
    private String deleteTaskString(TaskList tasks, int ID){
        return "   Noted. I've removed this task:\n" +
                "   " + tasks.getAllTasks().get(ID - 1).toString() + "\n" +
                "  Now you have " + (tasks.getAllTasks().size() - 1) + " tasks in the list.";
    }

    /**
     * gives the string for the new task list
     *
     * @param tasks removes the tasks to remove in TaskList.
     * @param ID of the task to remove
     * @return the string for the new task list
     */
    private String newTaskList(TaskList tasks, int ID){
        tasks.getAllTasks().remove(ID - 1);
        String s = "";
        for(int i = 0; i < tasks.getAllTasks().size(); i++){
            s = s + tasks.getAllTasks().get(i).inputListFormat() + "\n";
        }
        return s;
    }

    /**
     * updates the the file in storage after task is deleted.
     *
     * @param newList where this is the new input replaces the old input in the file
     * @param storage which contains file to be changed
     * @throws FileAbsentException when the file to be updated is absent in Storage
     */
    private void updateTaskList(String newList, Storage storage) throws FileAbsentException {
        try {
            FileWriter fw = new FileWriter(storage.getFilePath());
            fw.write(newList);
            fw.close();
        } catch (IOException i) {
            throw new FileAbsentException(storage.getFilePath());
        }
    }

    /**
     * This returns the string that the task has been deleted adn also updated the TakList.
     *
     * @param storage in which the file is updated.
     * @param tasks used to update the task for the task to be deleted.
     * @param ID of the task to be deleted.
     * @return
     * @throws DukeException throws if file is absent
     */
    private String rewrite(Storage storage, TaskList tasks, int ID)  throws DukeException{
        String newTaskList = newTaskList(tasks, ID);
        try {
            updateTaskList(newTaskList, storage);
            System.out.println(deleteTaskString(tasks, ID));
            return deleteTaskString(tasks, ID);
        } catch (FileAbsentException f) {
            throw new FileAbsentException(storage.getFilePath());
        }
    }

    /**
     * Returns whether the number is present.
     *
     * @return true is the number is absent and false if number is present.
     */
    private boolean numberAbsent(){
        return commandDescription.length() == 4 || commandDescription.length() == 5;
    }

    /**
     * Returns whether the task is present in the list.
     *
     * @param ID of task to be removed
     * @param tasks from which the task is to be removed.
     * @return true if the task is not present in list.
     */
    private boolean numberNotInList(int ID, TaskList tasks){
        return ID > tasks.getAllTasks().size();
    }

    /**
     * Deletes task and handles error
     *
     * @param tasks to change the taskList since item is deleted
     * @param ui
     * @param storage to change the file since item is deleted
     * @return String returns the string of the output that informs the delete action has been complete.
     * @throws DukeException thrown if the ID is more than number of ID is absent
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (numberAbsent()) {
           throw new DeleteException(true, false);
        }else{
            int ID = Integer.parseInt(commandDescription.substring(7));
            if (numberNotInList(ID, tasks)) {
                throw new DeleteException(false, true);
            }else {
                return rewrite(storage, tasks, ID);
            }
        }
    }

}
