import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The event is a subclass of Task and it is used to describe tasks that has to be completed by a specific day and time
 */
public class event extends Task {
    private String dateTime;
    private String end;
    /**
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     * @param dateTime assigns this.dayTime to dayTime value
     */
    public event(String name, String dateTime, String end) {
        super(name);
        this.dateTime = dateTime;
        this.end = end;
    }
    /**
     *  Overrides the toString methods
     * @return the specific representation for event class as mentioned with [E] indicating that it is a event class
     *      * and also mentions the event.
     */
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.dateTime +  "-" + this.end + ")";
    }
    public static LocalDate localDate(String string){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
            LocalDate parsedDate = LocalDate.parse(string, formatter);
            return parsedDate;
        }catch (DateTimeException d) {
            /*try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd, HH:mm");
                LocalDateTime parsedDate = LocalDateTime.parse(string, formatter);
                return parsedDate;
            } catch (DateTimeException g) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime parsedDate = LocalTime.parse(string, formatter);
                } catch (DateTimeException f) {
                    System.out.println(f.toString());
                }
            } */
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
    public static event provide(String name, String string, String end){
        event e;
        try{
            LocalDate parsedDate = localDate(string);
            LocalDate endDate = localDate(end);
            e = new event(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")),
                    endDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")));
        }catch (DateTimeException d) {
            try {
                LocalDateTime parsedDate = localDateTime(string);
                LocalDateTime endDate = localDateTime(end);
                e = new event(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")),
                        endDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")));
            } catch (DateTimeException g) {
                try {
                    LocalTime parsedDate = localTime(string);
                    LocalTime endDate = localTime(end);
                    e = new event(name, parsedDate.format(DateTimeFormatter.ofPattern("HH:mm")),
                            endDate.format(DateTimeFormatter.ofPattern("HH:mm")));
                } catch (DateTimeException f) {
                    System.out.println(f.toString());
                    throw f;
                }
            } }
        return e;

    }
    public String inputListFormat(){
        return "E" + super.inputListFormat() + " | " + this.dateTime + "-" + this.end;
    }
}