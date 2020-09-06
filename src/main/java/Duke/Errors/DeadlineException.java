package Duke.Errors;

/**
 * This DeadlineException is used to print out exceptions when there is an incomplete input where wither the description
 * or date is absent.
 */

public class DeadlineException extends DukeException{
    /**
     * descriptionPresent tests shows whether the description is present in the input of the user or not.
     * If description is not present it is true, else it is false
     */
    private boolean isDescriptionAbsent;
    private boolean isFormatWrong;
    private boolean isDateTimeAbsent;

    private final String DESCRIPTIONABSENT = "  '\u2639' OOPS!!! The description of a deadline cannot be empty.";
    private final String FORMATWRONG = "  '\u2639' OOPS!!! The formats of date and/ or include " +
            "yyyy MM dd/ yyyy MM dd, HH:mm/ HH:mm";
    private final  String DATETIMEABSENT = "  '\u2639' OOPS!!! The specific date/time of a deadline cannot be empty.";
    /**
     * constructor for deadline exception that assigns description and format values
     *
     * @param isDescriptionAbsent true if description absent
     * @param isFormatWrong true if format is wrong
     * @param isDateTimeAbsent true if date and time are absent.
     */
    public DeadlineException(boolean isDescriptionAbsent, boolean isFormatWrong, boolean isDateTimeAbsent){
        this.isDescriptionAbsent = isDescriptionAbsent;
        this.isFormatWrong = isFormatWrong;
        this.isDateTimeAbsent = isDateTimeAbsent;
    }

    /**
     * doesn't take in any arguments, overrides the in-built toString() method.
     *
     * @return returns a string informing that the description is empty if descriptionAbsent is true. Else, it tests
     * whether the format is wrong, if it then a string describing it would be returned. If isDateTimeAbsent is true then
     * date time for deadline is absent and a String describing it would be returned. Else then default returns which
     * should not occur.
     */
    public String toString(){
        if(this.isDescriptionAbsent) {
            return DESCRIPTIONABSENT;
        } else if(this.isFormatWrong) {
            return FORMATWRONG;
        } else if(this.isDateTimeAbsent) {
            return DATETIMEABSENT;
        } else {
            return "default";
        }
    }
}