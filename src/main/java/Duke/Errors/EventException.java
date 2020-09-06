package Duke.Errors;

/**
 * This EventException is used to print out exceptions when there is an incomplete input where whether the description
 * or date is absent.
 */
public class EventException extends DukeException {
    /**
     * description tests shows whether the description is present in the input of the user or not.
     * If description is not present it is true, else it is false
     */
    private boolean isDescriptionAbsent;
    private boolean isEndTimeAbsent;
    private boolean isStartAfterEnd;
    private boolean isDateWrongFormat;
    private boolean isDateEmpty;

    private final String DESCRIPTIONABSENT = "  '\u2639' OOPS!!! The description of an Event cannot be empty.";
    private final String ENDTIMEABSENT = "  '\u2639' OOPS!!! There should be 2 occurrences of date and/or time values.";
    private final String STARTAFTEREND = "  '\u2639' OOPS!!! Start should be less than end.";
    private final String DATEWRONGFORMAT = "  '\u2639' OOPS!!! Start and should be of the same format. The formats include " +
            "yyyy MM dd/ yyyy MM dd, HH:mm/ HH:mm";
    private final String DATEEMPTY = "  '\u2639' OOPS!!! The specific date of an Event cannot be empty.";
    /**
     *
     * @param isDescriptionAbsent input, depending on whether the description is given by user.
     * @param isEndTimeAbsent is true when the user has no input for end time
     * @param isStartAfterEnd is true when the start time is after end time
     * @param isDateWrongFormat is true when date is input in wrong format.
     * @param isDateEmpty is true when date is input in wrong format
     */
    public EventException(boolean isDescriptionAbsent, boolean isEndTimeAbsent, boolean isStartAfterEnd,
                          boolean isDateWrongFormat, boolean isDateEmpty){
        this.isDescriptionAbsent = isDescriptionAbsent;
        this.isEndTimeAbsent = isEndTimeAbsent;
        this.isStartAfterEnd = isStartAfterEnd;
        this.isDateWrongFormat = isDateWrongFormat;
        this.isDateEmpty = isDateEmpty;
    }

    /**
     * doesn't take in any arguments, overrides the in-built toString() method.
     * @return returns a string informing that the description is empty if description is true.
     * If isEndTimeAbsent is absent is true, end time is absent and then a description mentioning this would be returned.
     * Else, if isStartAfterEnd is true, start would be more than end then a description describing this would be printed
     * Else if ifDateWrongFormat is true, then the date is in wrong format and a description describing it would be printed
     * Else if isDateEmpty is true, then String giving that is returned.
     * Else default is returned.
     */
    public String toString(){
        if(this.isDescriptionAbsent){
            return DESCRIPTIONABSENT;
        }else if(this.isEndTimeAbsent) {
            return ENDTIMEABSENT;
        }else if(this.isStartAfterEnd) {
            return STARTAFTEREND;
        }else if(this.isDateWrongFormat) {
            return DATEWRONGFORMAT;
        }else if(this.isDateEmpty) {
            return DATEEMPTY;
        }else{
            return "default";
        }
    }
}