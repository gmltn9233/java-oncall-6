package oncall.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.exception.ProgrammerException;
import oncall.exception.ScheduleException;

public class Schedule {
    private final List<Programmer> schedule;

    public Schedule(String input) {
        List<String> schedule = parseInput(input);
        validateSchedule(schedule);
        this.schedule = convertProgrammers(schedule);
    }

    public List<Programmer> getSchedule() {
        return schedule;
    }

    private List<String> parseInput(String input) {
        validateInput(input);
        String[] token = input.split(",");
        List<String> schedule = new ArrayList<>();
        Collections.addAll(schedule, token);
        return schedule;
    }

    private void validateInput(String input) {
        if (isEmptyInput(input)) {
            throw new ScheduleException("입력이 비어있습니다.");
        }
        if (!hasComma(input)) {
            throw new ScheduleException("입력 형식은 [근무자,근무자] 형식입니다.");
        }
    }

    private boolean isEmptyInput(String input) {
        if (input == null || input.isEmpty() || input.isBlank()) {
            return true;
        }
        return false;
    }

    private boolean hasComma(String input) {
        if (input.contains(",")) {
            return true;
        }
        return false;
    }

    private void validateSchedule(List<String> schedule) {
        if (hasDuplicateName(schedule)) {
            throw new ProgrammerException("닉네임은 중복될 수 없습니다.");
        }
        if (hasContinuousProgrammer(schedule)) {
            throw new ScheduleException("근무자는 연속으로 근무 할 수 없습니다.");
        }
        if(schedule.size() < 5 || schedule.size() > 35){
            throw new ScheduleException("근무자는 최소 5명 최대 35명입니다.");
        }
    }

    private boolean hasDuplicateName(List<String> schedule) {
        Set<String> uniqueProgrammer = new HashSet<>(schedule);
        return uniqueProgrammer.size() != schedule.size();
    }

    private boolean hasContinuousProgrammer(List<String> schedule) {
        String previous = null;
        for (String current : schedule) {
            if (current.equals(previous)) {
                return true;
            }
            previous = current;
        }
        return false;
    }

    private List<Programmer> convertProgrammers(List<String> schedule) {
        List<Programmer> programmersSchedule = new ArrayList<>();
        for (String name : schedule) {
            programmersSchedule.add(new Programmer(name));
        }
        return programmersSchedule;
    }
}
