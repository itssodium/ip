package Duke.Errors;

/**
 * This exception is thrown when the file is empty
 */
public class FIleEmptyException extends DukeException {
    private final String FILEEMPTY = "Task file is empty!";
    /**
     * this overrides the toString() method
     *
     * @return a String representation of FileEmptyException
     */
    @Override
    public String toString() {
        return FILEEMPTY;
    }
}
