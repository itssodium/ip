package Duke.Commands;

import Duke.Errors.DukeException;
import Duke.Errors.EventException;
import Duke.Errors.FileAbsentException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.event;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * handles cases when event is keyword
 */
public class EventCommand extends AddCommand{
    /**
     * assigns string to a value of string
     * @param string assigns string to this this.string
     */
    public EventCommand(String string) {
        super(string);
    }

    private static event provide(String name, String string, String end) throws DukeException {
        event e;
        try{
            LocalDate parsedDate = stringToLocalDate(string);
            LocalDate endDate = stringToLocalDate(end);
            if(parsedDate.isAfter(endDate)){
                throw new EventException(false, false, true, false);
            }
            e = new event(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")),
                    endDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")));
        }catch (EventException event){
            throw new EventException(false, false, true, false);
        }catch (DateTimeException d) {
            try {
                LocalDateTime parsedDate = stringToLocalDateTime(string);
                LocalDateTime endDate = stringToLocalDateTime(end);
                if(parsedDate.isAfter(endDate)){
                    throw new EventException(false, false, true, false);
                }
                e = new event(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")),
                        endDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")));
            } catch (EventException event){
                throw new EventException(false, false, true, false);
            }
            catch (DateTimeException g) {
                try {
                    LocalTime parsedDate = stringToLocalTime(string);
                    LocalTime endDate = stringToLocalTime(end);
                    if(parsedDate.isAfter(endDate)){
                        throw new EventException(false, false, true, false);
                    }
                    e = new event(name, parsedDate.format(DateTimeFormatter.ofPattern("HH:mm")),
                            endDate.format(DateTimeFormatter.ofPattern("HH:mm")));
                }catch (EventException y){
                    throw new EventException(false, false, true, false);
                }catch (DateTimeException z) {
                    throw new EventException(false, false, false, true);
                }
            }
        }
        return e;
    }

    /**
     * is used to add event task or handle exceptions
     * @param tasks to change the taskList if necessary when no error
     * @param ui
     * @param storage to change the file in the if necessary when no error
     * @return String returns the string of the output that informs the action has been complete.
     * @throws DukeException if there no description after event no time or time is wrong format
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (commandDescription.length() == 5 || commandDescription.length() == 6) {
            throw new EventException(true, false, false, false);
        }
        String s = "";
        int index = -1;
        int end = -1;
        boolean duration = false;
        boolean time = false;
        String start = "";
        for (int i = 5; i < commandDescription.length(); i++) {
            if (commandDescription.charAt(i) == '/') {
                index = i;
                time = true;
                break;
            }
            s = s + commandDescription.charAt(i);
        }
        for (int i = index + 1; i < commandDescription.length(); i++) {
            if (commandDescription.charAt(i) == '-' && i != commandDescription.length() - 1) {
                end = i;
                duration = true;
                break;
            }
            start = start + commandDescription.charAt(i);
        }
        if (!time) {
            throw new EventException(false, false, false, false);
        }
        if (!duration) {
            throw new EventException(false, true, false, false);
        }
        event d = provide(s.substring(1, s.length() - 1), commandDescription.substring(index + 4, end), commandDescription.substring(end + 1));
        try {
            return updateTaskList(storage, d, tasks);

        } catch (IOException i) {
            throw new FileAbsentException(storage.getFilePath());
        }
    }
}
