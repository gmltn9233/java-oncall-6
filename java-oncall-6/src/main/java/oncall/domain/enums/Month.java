package oncall.domain.enums;

import oncall.exception.OncallException;

public enum Month {

    JAN(1,31),
    FEB(2,28),
    MAR(3,31),
    APR(4,30),
    MAY(5,31),
    JUN(6,30),
    JUL(7,31),
    AUG(8,31),
    SEP(9,30),
    OCT(10,31),
    NOV(11,30),
    DEC(12,31);

    private final int idx;
    private final int day;

    Month(int idx, int day){
        this.idx = idx;
        this.day = day;
    }

    public static int getDay(int idx){
        for(Month month : values()){
            if(idx == month.idx){
                return month.day;
            }
        }
        throw new OncallException("올바르지 않은 월입니다.");
    }
}
