package Duke.Commands;

import Duke.Errors.DoneException;
import Duke.Errors.DukeException;
import Duke.Errors.FileAbsentException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

import java.io.FileWriter;
import java.io.IOException;

/**
 * handles case when done is keyword
 */
public class DoneCommand extends Command {
    /**
     * assigns string to a value of string
     * @param string assigns string to this this.string
     */
    public DoneCommand(String string) {
        super(string);
    }

    /**
     * Completes done task and handle error
     * @param tasks to change the taskList as a task is completed
     * @param ui
     * @param storage to change the file as task is completed
     * @return String returns the string of the output that informs the done action has been complete.
     * @throws DukeException thrown if the ID is more than number of ID is absent
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isNumberAbsent()) {
            throw new DoneException(true, false);
        }else{
            int ID = Integer.parseInt(commandDescription.substring(5));
            if (isNumberNotInList(ID, tasks)) {
                throw new DoneException(false, true);
            } else {
                return rewrite(storage, tasks, ID);
            }
        }
    }
    /**
     * Returns whether the number is present.
     *
     * @return true is the number is absent and false if number is present.
     */
    private boolean isNumberAbsent(){
        return commandDescription.length() == 4 || commandDescription.length() == 5;
    }

    /**
     * Returns whether the task is present in the list.
     *
     * @param ID of task to be removed
     * @param tasks from which the task is to be removed.
     * @return true if the task is not present in list.
     */
    private boolean isNumberNotInList(int ID, TaskList tasks){
        return ID > tasks.getAllTasks().size();
    }

    /**
     * updates the the file in storage after task is marked as done.
     *
     * @param newList where this is the new input replaces the old input in the file.
     * @param storage which contains file to be changed.
     * @throws FileAbsentException when the file to be updated is absent in Storage.
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
     * gives the string for the new task list
     *
     * @param tasks marks the task as done
     * @param ID of the task to be marked as done
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
     * Returns the String informing that the task that is marked as done
     *
     * @param tasks uses to give the current number of tasks.
     * @param ID uses to get the task to mark done.
     * @return String informing that the task is marked as done.
     */
    private String doneMessage(TaskList tasks, int ID){
        return "   Nice! I've marked this task as done:\n" +
                "   " + tasks.getAllTasks().get(ID - 1).toString();
    }
    /**
     * This returns the string that the task has been deleted adn also updated the TakList.
     *
     * @param storage in which the file is updated.
     * @param tasks used to update the task for the task to mark as done.
     * @param ID of the task to mark as done.
     * @return
     * @throws DukeException throws if file is absent
     */
    private String rewrite(Storage storage, TaskList tasks, int ID) throws DukeException {
        tasks.getAllTasks().get(ID - 1).setDone(true);
        String s = newTaskList(tasks, ID);
        try {
            updateTaskList(s, storage);
            return doneMessage(tasks, ID);
        } catch (FileAbsentException f) {
            throw new FileAbsentException(storage.getFilePath());
        }
    }



}
