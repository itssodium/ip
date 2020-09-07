package Duke.Errors;

/**
 * This DoneException is used to print out exceptions when there is an incomplete input where the ID is absent or the ID
 * of that Task hasnt been defined yet.
 */
public class DoneException extends DukeException{
    /**
     * IDabsent tests shows whether the ID is present in the input of the user or not.
     * If ID is not present it is true, else it is false
     */
    private boolean isIDAbsent;
    private boolean isNotIDDefined;

    /**
     * constructor that assigns tne 2 variables its respective values
     * @param isIDabsent input, depending on whether the ID is present or not in the input.txt file. If present it is false
     *   else it is true.
     * @param isNOtIDDefined input, true if ID > number of tasks present, false otherwise.
     */
    public DoneException(boolean isIDabsent, boolean isNOtIDDefined){
        this.isIDAbsent = isIDabsent;
        this.isNotIDDefined = isNOtIDDefined;
    }

    /**
     * doesn't take in any arguments, overrides the in-built toString() method.
     *
     * @return returns a string depending on the scenario. If the IDabsent is true, then description that the description of
     * done cannot be empty.Else if isDefined is true then String returning that ID is not defined is returned. Else,
     *  default is returned which should not occur.
     */
    @Override
    public String toString() {
        if(isIDAbsent){
            return iDAbsent();
        }else if(isNotIDDefined){
            return iDNotDefined();
        }else {
            return "default";
        }
    }

    /**
     *
     *
     * @return
     */
    public String iDAbsent(){
        return "  '\u2639' OOPS!!! The description of done cannot be empty.";
    }

    public String iDNotDefined(){
        return "  '\u2639' OOPS!!! The ID is not yet defined.";
    }
}