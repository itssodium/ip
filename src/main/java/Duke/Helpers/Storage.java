package Duke.Helpers;

import Duke.Errors.DukeException;
import Duke.Errors.FIleEmptyException;
import Duke.Errors.FileAbsentException;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.ToDo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;
    /**
     * Constructor assigns filePath to filePath
     * @param filePath assigns this value to variable
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Converts the string form of tasks on the file to Task objects
     *
     * @return the List of tasks containing Task instead of String
     * @throws DukeException when a file with FilePath doesnt exist.
     */
    public List<Task> load() throws DukeException {
        File f = new File(this.filePath);
        assert f.exists();
        try {
            List<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(f);
            if(sc.hasNext()) {
                do{
                    String s = sc.nextLine();
                    char bool = s.charAt(4);
                    boolean done = (bool == '1');
                    if(s.charAt(0) == 'T'){
                        assert !s.substring(8).contains("|");
                        tasks.add(new ToDo(s.substring(8), done));
                    }else if(s.charAt(0) == 'E'){
                        String string = "";
                        int index = -1;
                        for(int i = 8; i < s.length(); i++){
                            if(s.charAt(i) == '|'){
                                index = i;
                                break;
                            }
                            string = string + s.charAt(i);
                        }
                        String another = "";
                        for(int i = index + 2; i < s.length(); i++){
                            if(s.charAt(i) == '-'){
                                index = i;
                                break;
                            }
                            another = another + s.charAt(i);
                        }
                        assert !string.contains("|");
                        assert !another.contains("|");
                        assert !s.substring(index + 1).contains("|");
                        tasks.add(new Event(string, done, another, s.substring(index + 1)));
                    }else {
                        String string = "";
                        int index = -1;
                        for(int i = 8; i < s.length(); i++){
                            if(s.charAt(i) == '|'){
                                index = i;
                                break;
                            }
                            string = string + s.charAt(i);
                        }
                        assert !string.contains("|");
                        assert !s.substring(index +2).contains("|");
                        tasks.add(new Deadline(string, done, s.substring(index + 2)));
                    }
                } while (sc.hasNextLine());
            }
            if(tasks.size() == 0){
                throw new FIleEmptyException();
            }else {
                return tasks;
            }
        } catch (IOException error){
            throw new FileAbsentException(this.filePath);
        }
    }
    /**
     * gives the filePath
     * @return the value of filePath
     */
    public String getFilePath() {
        return filePath;
    }
}
