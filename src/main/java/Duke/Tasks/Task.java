package Duke.Tasks;
import java.util.ArrayList;
import java.util.List;

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
    private boolean done;
    private String name;

    /**
     * constructor assigns name variable a value
     * @param name this assigns the name of the Task to the name being given in the constructor.
     */
    Task(String name) {
        this.done = false;
        this.name = name;
        tasks.add(this);
    }

    /**
     * constructor assigns name and done a value.
     * @param name assigns name to this.name
     * @param done assigns name to this.done
     */
    Task(String name, boolean done){
        this.done = done;
        this.name = name;
        tasks.add(this);
    }
    /**
     * gives name of task
     * @return name of task
     */
    public String getName() {
        return name;
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