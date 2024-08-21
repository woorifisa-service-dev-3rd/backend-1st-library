package dev.service.cloud.domain.student;

import java.sql.Date;

public class Student {
    private long id;
    private String name;
    private Date birth;
    private String address;

    public Student(long id, String name, Date birth, String address) {
        super();
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.address = address;
    }

    @Override
    public String toString(){
        return "Student [id=" + id + ", name=" + name + ", birth=" + birth + ", address=" + address;
    }
}
