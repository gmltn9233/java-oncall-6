package oncall.domain.enums;

import oncall.exception.OncallException;

public enum DayOfWeek {
    월(0,"월"),
    화(1,"화"),
    수(2,"수"),
    목(3,"목"),
    금(4,"금"),
    토(5,"토"),
    일(6,"일");
    private final int idx;
    private final String dayOfWeek;

    private static final DayOfWeek[] VALUES = values();

    DayOfWeek(int idx, String dayOfWeek){
        this.idx = idx;
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public static DayOfWeek getDayOfWeek(String input) {
        for(DayOfWeek dayofWeek : VALUES){
            if(input.equals(dayofWeek.dayOfWeek)){
                return dayofWeek;
            }
        }
        throw new OncallException("요일이 없습니다.");
    }

    public DayOfWeek nextDay() {
        return VALUES[(this.idx + 1) % VALUES.length]; // 로테이션
    }

    public static boolean isWeekend(DayOfWeek dayOfWeek){
        if(dayOfWeek.equals(DayOfWeek.토) || dayOfWeek.equals(DayOfWeek.일)){
            return true;
        }
        return false;
    }
}
