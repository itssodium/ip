package Duke.Commands;
import Duke.Errors.DeadlineException;
import Duke.Errors.DukeException;
import Duke.Errors.EventException;
import Duke.Errors.TodoException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.Deadline;
import Duke.Tasks.Task;
import Duke.Tasks.event;
import Duke.Tasks.todo;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {
    public AddCommand(String string) {
        super(string);
    }
    public static void update(Storage storage, Task task, TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(storage.getFilePath(), true);
        taskList.getAllTasks().add(task);
        fw.write(task.inputListFormat() + "\n");
        fw.close();
        Task.tasks.add(task);
        System.out.println("  Got it. I've added this task:\n  " + task.toString() + "\n" +
                "  Now you have " + taskList.getAllTasks().size() + " tasks in the list.");
    }
    public static LocalDate localDate(String string){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
            LocalDate parsedDate = LocalDate.parse(string, formatter);
            return parsedDate;
        }catch (DateTimeException d) {
            throw d;
        }
    }
    public static LocalDateTime localDateTime(String string){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd, HH:mm");
            LocalDateTime parsedDate = LocalDateTime.parse(string, formatter);
            return parsedDate;
        } catch (DateTimeException g) {
            throw g;
        }
    }
    public static LocalTime localTime(String string){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime parsedDate = LocalTime.parse(string, formatter);
            return parsedDate;
        } catch (DateTimeException f) {
            throw f;
        }
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);
        if (string.length() >= 4 && string.substring(0, 4).equals("todo")) {
            if (string.length() == 4 || string.length() == 5) {
                throw new TodoException();
            } else {
                todo t = new todo(string.substring(5));
                t.output(storage.getFilePath());
            }
        } else if (string.length() >= 5 && string.substring(0, 5).equals("event")) {
            if (string.length() == 5 || string.length() == 6) {
                throw new EventException(true, false, false, false);
            }
            String s = "";
            int index = -1;
            int end = -1;
            boolean duration = false;
            boolean time = false;
            String start = "";
            for (int i = 5; i < string.length(); i++) {
                if (string.charAt(i) == '/') {
                    index = i;
                    time = true;
                    break;
                }
                s = s + string.charAt(i);
            }
            for (int i = index + 1; i < string.length(); i++) {
                if (string.charAt(i) == '-' && i != string.length() - 1) {
                    end = i;
                    duration = true;
                    break;
                }
                start = start + string.charAt(i);
            }
            if (!time) {
                throw new EventException(false, false, false, false);
            }
            if (!duration) {
                throw new EventException(false, true, false, false);
            }
            event d = event.provide(s.substring(1, s.length() - 1), string.substring(index + 4, end), string.substring(end + 1));
            d.output(storage.getFilePath());
        } else if (string.length() >= 8 && string.substring(0, 8).equals("deadline")) {
            if (string.length() == 8 || string.length() == 9) {
                throw new DeadlineException(true, false);
            }
            String s = "";
            int index = -1;
            boolean time = false;
            for (int i = 8; i < string.length(); i++) {
                if (string.charAt(i) == '/') {
                    index = i;
                    time = true;
                    break;
                }
                s = s + string.charAt(i);
            }
            if (!time) {
                throw new DeadlineException(false, false);
            }
            Deadline d = Deadline.provide(s.substring(1, s.length() - 1), string.substring(index + 4));
            d.output(storage.getFilePath());
        }
    }
}
