public class EventException extends Exception {
    boolean description;
    boolean time;
    EventException(boolean description, boolean time){
        this.description = description;
        this.time = time;
    }
    public String toString(){
        if(this.description){
            return "  '\u2639' OOPS!!! The description of an event cannot be empty.\n" +
                    "  ____________________________________________________________";
        }
        return "  '\u2639' OOPS!!! The specific date of an event cannot be empty.\n" +
                "  ____________________________________________________________";
    }
}