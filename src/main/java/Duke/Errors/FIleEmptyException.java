package Duke.Errors;

/**
 * This exception is thrown when the file is empty
 */
public class FIleEmptyException extends DukeException {
    /**
     * this overrides the toString() method
     *
     * @return a String representation of FileEmptyException
     */
    @Override
    public String toString() {
        return fileEmpty(); //when file is empty
    }

    /**
     * Returns when file is empty
     *
     * @return informs user that file is empty
     */
    public String fileEmpty(){
        return "  Task file is empty!";
    }
}
