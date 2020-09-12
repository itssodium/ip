package duke.tasks;

/**
 * The deadline is a subclass of Task and it is used to describe tasks that has to be completed by a specific day.
 */
public class Deadline extends Task {
    private String day = null;
    /**
     * Assigns the name and day values
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     * @param day assigns this.day to day value
     */
    public Deadline(String name, String day) {
        super(name);
        this.day = day;
    }

    /**
     * Assigns the nam, done and day values
     *
     * @param name argument in super class constructor
     * @param done argument in super class constructor
     * @param day assigns to this.day
     */
    public Deadline(String name, boolean done, String day) {
        super(name, done);
        this.day = day;
    }

    /**
     * Takes no arguments and overrides the toString method
     *
     * @return the specific representation for deadline class as mentioned with [D]
     * indicating that it is a deadline class
     * and also mentions the deadline.
     */
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.day + ")";
    }

    /**
     * Gives a specific string representation for that in the tasks.txt file and overrides that in Task to make
     * it unique to that for Deadline
     *
     * @return the string representation
     */
    public String inputListFormat() {
        return "D" + super.inputListFormat() + " | " + this.day; //format of Tasks to appear in file in Storage
    }

}
