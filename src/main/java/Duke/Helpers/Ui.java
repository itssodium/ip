package Duke.Helpers;

import Duke.Errors.DukeException;
import Duke.Errors.FileAbsentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * deals with interactions with the user
 */
public class Ui {
    private DukeException dukeException;
    private Scanner sc;
    public Ui(){
        sc =  new Scanner(System.in);
    }
    public Ui(String file) throws DukeException {
        try {
            sc = new Scanner(new File(file));
        }catch (FileNotFoundException f){
            sc = new Scanner(System.in);
            throw new FileAbsentException(file);
        }

    }
    public void setDukeException(DukeException dukeException){
        this.dukeException = dukeException;
    }
    /**
     * This prints out if there is an error when tasks are loaded
     */
    public void showLoadingError(){
        System.out.println(dukeException.getMessage());
    }
    /**
     * prints welcome message
     */
    public void showWelcome(){
        System.out.println("  ____________________________________________________________\n" +
                "  Hello! I'm Duke\n" + "  What can I do for you?");
    }

    /**
     * This prints the ____ for easier readability
     */
    public void showLine(){
        System.out.println("  ____________________________________________________________\n");
    }

    /**
     * This prints the next line of code to execute if it exists
     *
     * @return the string of command
     */
    public String readCommand(){
        if(sc.hasNextLine()){
            return sc.nextLine();
        }else{
            return null;
        }
    }

    /**
     * Prints out the error
     *
     * @param s s is the error that is printed
     */
    public void showError(String s){
        System.out.println(s);
    }
}
