package oncall.controller;

import oncall.domain.Calender;
import oncall.domain.Schedule;
import oncall.domain.Scheduler;
import oncall.exception.OncallException;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {
    public void go(){
        Calender calender = makeCalender();
        makeSchedule(calender);
    }

    private Calender makeCalender(){
        while(true){
            try{
                String calenderRequest = InputView.calenderRequest();
                Calender calender = new Calender(calenderRequest);
                return calender;
            }catch (OncallException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void makeSchedule(Calender calender){
        while(true){
            try{
                String scheduleRequest = InputView.scheduleRequest();
                Schedule schedule = new Schedule(scheduleRequest);
                String holidayScheduleRequest = InputView.holidayScheduleRequest();
                Schedule holidaySchedule = new Schedule(holidayScheduleRequest);
                Scheduler scheduler = new Scheduler(calender,schedule,holidaySchedule);
                scheduler.setProgrammer();
                OutputView.printOutput(calender);
                return;
            }catch (OncallException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
