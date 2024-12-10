package oncall.domain;

import oncall.exception.ProgrammerException;

public class Programmer {
    private final String name;

    public Programmer(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name){
        if(name.length() > 5){
            throw new ProgrammerException("닉네임은 최대 5글자 입니다.");
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Programmer programmer = (Programmer) obj;
        return this.name.equals(programmer.name);
    }
}
