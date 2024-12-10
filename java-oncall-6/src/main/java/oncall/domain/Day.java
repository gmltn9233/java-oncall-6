package oncall.domain;

import java.time.MonthDay;
import oncall.domain.enums.DayOfWeek;
import oncall.domain.enums.Holiday;

public class Day {
    private final MonthDay monthDay;
    private final DayOfWeek dayOfWeek;

    private Programmer programmer;

    public Day(MonthDay monthDay, DayOfWeek dayOfWeek){
        this.monthDay = monthDay;
        this.dayOfWeek = dayOfWeek;
    }


    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    public boolean isHoliday(){
        if(Holiday.isHoliday(monthDay) || DayOfWeek.isWeekend(dayOfWeek)) return true;
        return false;
    }


    @Override
    public String toString() {
        int month = monthDay.getMonthValue();
        int day = monthDay.getDayOfMonth();
        String holiday = "";
        if (Holiday.isHoliday(monthDay) && !Holiday.isHoliday(monthDay)) holiday = "(휴일)";
        return String.format("%d월 %d일 %s%s %s",month,day,dayOfWeek.getDayOfWeek(),holiday, programmer);
    }
}
