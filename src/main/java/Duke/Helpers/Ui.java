package Duke.Helpers;

import Duke.Duke;
import Duke.Errors.DukeException;

import java.util.Scanner;

public class Ui {
    String curr;
    int currNum = 0;
    Scanner sc = new Scanner(System.in);
    public void showLoadingError(DukeException d){
        System.out.println(d.getMessage());
    }
    public void showWelcome(){
        System.out.println("  ____________________________________________________________\n" +
                "  Hello! I'm Duke\n" + "  What can I do for you?");
    }
    public void showLine(){
        System.out.println("  ____________________________________________________________\n");
    }
    public String readCommand(){
        if(sc.hasNext()){
            return sc.nextLine();
        }else{
            return null;
        }
    }
    public void curr(){
        System.out.println(this.curr);
    }
    public void showError(String s){
        System.out.println(s);
    }
    public void increment(){
        currNum++;
    }
    public int getCurrNum(){
        return this.currNum;
    }
    public String getCurr(){
        return this.curr;
    }
    public void setCurrNum(int currNum){
        this.currNum = currNum;
    }
    public void setCurr(String curr) {
        this.curr = curr;
    }
}
