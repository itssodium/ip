import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<String> todos = new ArrayList<>();
    private static void scan(){
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()) {
            do {
                todos.add(sc.nextLine());
            } while (sc.hasNextLine());
        }
    }
    public static void output(){
        System.out.println("  ____________________________________________________________\n" + "  Hello! I'm Duke\n" + "  What can I do for you?\n" +
                "  ____________________________________________________________");
        for(String string : todos){
            System.out.println("\n" + string + "\n  ____________________________________________________________");
            if(string.equals("bye")){
                System.out.println("  Bye. Hope to see you again soon!\n" + "  ____________________________________________________________");
                break;
            }
            if(string.equals("list")){
                Task.listing();
            }else if(string.substring(0,4).equals("done")){
                int ID = Integer.parseInt(string.substring(5));
                Task.tasks.get(ID - 1).setDone();
                Task.tasks.get(ID - 1).donePrint();
            }else {
                Task t = new Task(string);
            }
        }
    }

    public static void main(String[] args) {
        scan();
        output();
    }
}