package Duke.Tasks;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Duke.Errors.DukeException;
import Duke.Errors.FileAbsentException;

/**
 * This Task is made abstract because it is never intialized in the actual code, however, it is used so that polymorphism
 * is able to work properly.
 */
abstract public class Task {
    /**
     * Tasks is made static because it contains the different tasks that are added, and therefore it is not limited to
     * a single instance of Task
     */
    public static List<Task> tasks = new ArrayList<>();
    private static int num = 0;
    private boolean done;
    private String name;
    private int ID;
    private boolean deleted;

    /**
     * @param name this assigns the name of the Task to the name being given in the constructor. Then assigns done to be
     *             false as it is not completed when it is being defined. The ID number is assigned to be prev size of Tasks
     *             plus one because it is a task that is added now and this task is added to tasks.
     */
    Task(String name) {
        this.done = false;
        this.name = name;
        num++;
        this.ID = num;
        this.deleted = false;
        tasks.add(this);
    }

    /**
     * setter that sets Done to the done value stated
     * @param done value given to set it to done var
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * gives a specific string representation for that in the tasks.txt file
     * @return the string representation
     */
    public String inputListFormat(){
        if(this.done){
            return " | 1 | " + this.name;
        }else{
            return " | 0 | " + this.name;
        }
    }
    /**
     * Overrides the toString methods
     *
     * @return String which contains info on task name as well as whether it is completed(tick sign) or not(cross sign).
     */
    public String toString() {
        if (this.done) {
            return "[" + "\u2713" + "] " + this.name;
        }
        return "[" + "\u2717" + "] " + this.name;
    }

}