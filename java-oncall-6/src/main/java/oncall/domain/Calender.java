package oncall.domain;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import oncall.domain.enums.DayOfWeek;
import oncall.domain.enums.Month;
import oncall.exception.CalenderException;

public class Calender {
    private final List<Day> calender;

    public Calender(String input){
        validateInput(input);
        this.calender = makeCalender(input);
    }

    public List<Day> getCalender(){
        return calender;
    }
    private void validateInput(String input){
        if(isEmptyInput(input)){
            throw new CalenderException("입력이 비어있습니다.");
        }
        if(!hasComma(input)){
            throw new CalenderException("입력 형식은 [월,요일] 형식입니다.");
        }
    }

    private boolean isEmptyInput(String input){
        if(input == null || input.isEmpty() || input.isBlank()){
            return true;
        }
        return false;
    }

    private boolean hasComma(String input){
        if(input.contains(",")){
            return true;
        }
        return false;
    }

    private List<Day> makeCalender(String input){
        List<Day> calender = new ArrayList<>();
        String parts[] = input.split(",");
        int month = Integer.parseInt(parts[0]);
        DayOfWeek dayOfWeek = DayOfWeek.getDayOfWeek(parts[1]);
        int monthOfDay = Month.getDay(month);
        for(int i=1; i<=monthOfDay; i++){
            Day day = makeDay(month,i,dayOfWeek);
            dayOfWeek = dayOfWeek.nextDay();
            calender.add(day);
        }
        return calender;
    }

    private Day makeDay(int month, int day, DayOfWeek dayOfWeek){
        MonthDay monthDay = MonthDay.of(month,day);
        return new Day(monthDay, dayOfWeek);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Day day : this.calender) {
            output.append(day.toString()).append("\r\n");
        }

        // 마지막 개행문자 제거
        if (output.length() > 0) {
            output.setLength(output.length() - 1);
        }

        return output.toString();
    }
}


