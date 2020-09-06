package Duke.Errors;

/**
 * This exception is thrown whenever there is a file absent at a specific path mentioned in the filePath
 */
public class FileAbsentException extends DukeException {
    private String isFilePathAbsent;

    private String FILEABSENT = "  The file in this directory " + this.isFilePathAbsent + " is absent!";
    /**
     * This assigns filePath variable to a value
     *
     * @param isFilePathAbsent the value assigned to filePath
     */
    public FileAbsentException(String isFilePathAbsent){
        this.isFilePathAbsent = isFilePathAbsent;
    }

    /**
     * This overrides the toString() method
     *
     * @return a String for the exception is printed.
     */
    @Override
    public String toString() {
        return FILEABSENT;
    }
}
