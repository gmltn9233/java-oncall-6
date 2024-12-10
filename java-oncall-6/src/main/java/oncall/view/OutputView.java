package oncall.view;

import oncall.domain.Calender;
import oncall.domain.Day;

public class OutputView {

    public static void printOutput(Calender calender){
        System.out.print(calender.toString());
    }
}
