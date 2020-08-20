

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
    private static int num = 0;
    private boolean done;
    private String name;
    private int ID;
    private boolean deleted;
    /**
     *
     * @param name this assigns the name of the Task to the name being given in the constructor. Then assigns done to be
     *             false as it is not completed when it is being defined. The ID number is assigned to be prev size of Tasks
     *             plus one because it is a task that is added now and this task is added to tasks.
     *
     *
     */
    Task(String name){
        this.done = false;
        this.name = name;
        num++;
        this.ID = num;
        this.deleted = false;
        tasks.add(this);
    }

    /**
     * this prints out that this task is added successfully to the task.
     */
    public void output(){
        System.out.println("  Got it. I've added this task:\n  " + this.toString() + "\n" +
                "  Now you have " + tasks.size() + " tasks in the list.\n" + "  ____________________________________________________________");
    }

    /**
     *
     * @param ID is the ID of tShe task that you wish to delete. Since it needs to delete task from tasks, which is static,
     *           this function is also static. If the ID is not present in tasks, it prints that it is not present. If task
     *           is previously deleted, it prints that it was deleted.
     *           Else, it prints that it is successfully deleted.
     */
    public static void deleteDone(int ID){
        if(ID > num){
            System.out.println(new DeleteException(false, false).toString());
        }else {
                Task task = tasks.get(ID - 1);
                if(!task.deleted) {
                    tasks.get(ID - 1).deleted = true;
                    System.out.println("   Noted. I've removed this task:");
                    System.out.println("   " + tasks.get(ID - 1).toString());
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("  ____________________________________________________________");
                }else{
                    System.out.println(new DeleteException(false, true).toString());
                }
        }
    }

    /**
     *
     * @param ID is the ID of the task that you wish to set as done. Since it needs to assign truth to task from tasks,
     *           which is static, this function is also static. If the ID is not present in tasks, it prints that it is not present.
     *           If the task is deleted, it would print that it was deleted.
     *           Else, it prints that it is successfully completed.
     */
    public static void setDone(int ID){
        if(ID > num){
            System.out.println(new DoneException(false, false).toString());
        }else {
            Task task = tasks.get(ID - 1);
            if(task.deleted) {
                System.out.println(new DoneException(false, true).toString());
            }else {
                tasks.get(ID - 1).done = true;
                System.out.println("   Nice! I've marked this task as done:");
                System.out.println("   " + tasks.get(ID - 1).toString());
                System.out.println("  ____________________________________________________________");
            }
        }
    }

    /**
     * This prints out all tje Since it needs to assign truth to task from tasks,
     *                 which is static, this function is also static. Then it prints all the tasks curr present in Tasks
     *                 and also gives info on whether it is present or not.
     */
    public static void listing(){
        System.out.println("   Here are the tasks in your list:");
        for(Task task : tasks){
            if(!task.deleted) {
                System.out.println("  " + task.ID + "." + task.toString());
            }
        }
        System.out.println("  ____________________________________________________________");
    }

    /**
     *  Overrides the toString methods
     * @return String which contains info on task name as well as whether it is completed(tick sign) or not(cross sign).
     */
    public String toString(){
        if(this.done) {

            return "[" + "\u2713" + "] " + this.name;
        }
        return "[" + "\u2717" + "] " + this.name;
    }
}
