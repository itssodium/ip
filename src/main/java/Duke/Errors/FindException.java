package Duke.Errors;

/**
 * The class FindException deals with what happens when an error occurs for task with find keyword
 */
public class FindException extends DukeException {
    private boolean noMatches;
    private boolean isDescriptionAbsent;
    private String description;

    private final String NOMATCHES = "there are no matches to your keyword: " + description;
    private final String DESCRIPTIONABSENT = "description of find cannot be empty!";
    /**
     * constructor assigns values of description and string
     *
     * @param isDescriptionAbsent value is assigned to this.description
     * @param
     * @param description value is assigned to this.string
     */
    public FindException(boolean noMatches, boolean isDescriptionAbsent,  String description){
        this.noMatches = noMatches;
        this.isDescriptionAbsent = isDescriptionAbsent;
        this.description = description;
    }

    /**
     * overrides the toString() method
     *
     * @return if description is present error is due to no matches being present and an error message informing
     * them would be printed. If it is not present than error is due to keywords being absent therefore an error
     * message regarding that would be released.
     */
    public String toString(){
        if(noMatches){
            return NOMATCHES;
        } else if(isDescriptionAbsent) {
            return DESCRIPTIONABSENT;
        } else{
            return "default";
        }
    }
}
