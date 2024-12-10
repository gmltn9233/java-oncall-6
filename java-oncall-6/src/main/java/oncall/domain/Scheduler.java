package oncall.domain;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import oncall.exception.ScheduleException;

public class Scheduler {
    private final Calender calender;
    private final Schedule schedule;
    private final Schedule holidaySchedule;

    public Scheduler(Calender calender, Schedule schedule, Schedule holidaySchedule){
        this.calender = calender;
        this.schedule = schedule;
        this.holidaySchedule = holidaySchedule;
    }

    public void setProgrammer(){
        Iterator<Programmer> regularIterator = createCyclicIterator(schedule.getSchedule());
        Iterator<Programmer> holidayIterator = createCyclicIterator(holidaySchedule.getSchedule());
        Queue<Programmer> queue = new LinkedList<>();

        Programmer prevProgrammer = null;
        for (Day day : calender.getCalender()) {
            Programmer currentProgrammer;

            // 큐에 프로그래머가 존재하면 먼저 큐에서 가져옴
            if (!queue.isEmpty()) {
                currentProgrammer = queue.poll();
            } else {
                // 큐가 비어 있으면 다음 프로그래머를 가져옴
                currentProgrammer = getCurrentProgrammer(day, regularIterator, holidayIterator);
            }

            // 이전 프로그래머와 현재 프로그래머가 같다면 교체 로직 실행
            if (currentProgrammer.equals(prevProgrammer)) {
                queue.offer(currentProgrammer); // 중복된 프로그래머를 큐에 저장
                currentProgrammer = getCurrentProgrammer(day, regularIterator, holidayIterator); // 다음 프로그래머로 교체
            }

            // 현재 프로그래머를 배정
            day.setProgrammer(currentProgrammer);

            // 이전 프로그래머를 업데이트
            prevProgrammer = currentProgrammer;
        }
    }

    private Programmer getCurrentProgrammer(Day day,Iterator<Programmer> regularIterator, Iterator<Programmer> hoiidayIterator){
        if(day.isHoliday()){
            return hoiidayIterator.next();
        }
        return regularIterator.next();
    }

    private Iterator<Programmer> createCyclicIterator(List<Programmer> programmers) {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return !programmers.isEmpty(); // 빈 리스트 방지
            }

            @Override
            public Programmer next() {
                Programmer nextProgrammer = programmers.get(index);
                index = (index + 1) % programmers.size(); // 로테이션
                return nextProgrammer;
            }
        };
    }
}
