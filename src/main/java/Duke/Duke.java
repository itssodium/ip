package Duke;

import Duke.Commands.Command;
import Duke.Errors.DukeException;
import Duke.Helpers.Parser;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

import java.io.IOException;
/**
 * This Duke class is the main class that prints out the relevant outputs by including all the subclasses of Task and
 * taking in the input.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    public Duke(){}

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input)  {
        if(input == null){
            return "Hello";
        }else {
            Command c = Parser.parse(input);
            try {
                return c.execute(tasks, new Ui(), storage);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }

    }
    /**
     * assigns the above member variables with the appropriate values, and throws certain exceptions if file in
     * the filePath mentioned is empty or absent
     * @param filePath represents where the filepath of where the file may exist.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }
    public Duke(String filePath, String input){
        storage = new Storage(filePath);
        try {
            ui = new Ui(input);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }
    /**
     * gives main logic of the App,
     * where exceptions are caught and printed and if bye is there code stops. also starts with hello
     */
    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /**
     *
     * @param args of type String[]
     * reads input using scan() and adds it to todos.
     *  Then, prints out relevant information using the output() func.
     */
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("src/main/java/tasks.txt");
        String s = duke.getResponse("todo read book");
        /*PrintStream fileOut = new PrintStream("src/main/java/output.txt");
        System.setOut(fileOut);
        FileWriter fw = new FileWriter("src/main/java/tasks.txt");
        fw.write("");
        fw.close();
        Duke duke = new Duke("src/main/java/tasks.txt", "src/main/java/input.txt");
        duke.run();*/
    }
}

