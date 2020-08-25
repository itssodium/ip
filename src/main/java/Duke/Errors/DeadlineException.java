package Duke.Errors;

/**
 * This DeadlineException is used to print out exceptions when there is an incomplete input where wither the description
 * or date is absent.
 */

public class DeadlineException extends DukeException{
    /**
     * description tests shows whether the description is present in the input of the user or not.
     * If description is not present it is true, else it is false
     */
    private boolean description;
    private boolean format;

    /**
     *constructor for deadline exception that assigns description and format values
     * @param description input, depneding on whether the description is present or not in the input.txt file. If present it is false
     *   else it is true.
     */
    public DeadlineException(boolean description, boolean format){
        this.description = description;
        this.format = format;
    }

    /**
     * doesn't take in any arguments, overrides the in-built toString() method.
     * @return returns a string informing that the description is empty if description is true. Else, if description is
     * false, it means that the date is absent and it informs accordingly.
     */
    public String toString(){
        if(this.description){
            return "  '\u2639' OOPS!!! The description of a deadline cannot be empty.";
        }
        if(this.format){
            return "  '\u2639' OOPS!!! The formats of date and/ or include " +
                    "yyyy MM dd/ yyyy MM dd, HH:mm/ HH:mm";
        }
        return "  '\u2639' OOPS!!! The specific date/time of a deadline cannot be empty.";
    }
}