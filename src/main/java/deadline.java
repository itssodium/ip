
public class deadline extends Task {
    String day;
    public deadline(String name, String day) {
        super(name);
        this.day = day;
    }
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.day + ")";
    }
}