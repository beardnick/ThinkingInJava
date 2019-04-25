package io;

import java.io.Serializable;

public class Student implements Serializable {

    private String name;

    private Long id;

    private String sex;

    private  transient String password;


    public Student(String name, Long id, String sex, String password) {
        this.name = name;
        this.id = id;
        this.sex = sex;
        this.password = password;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getSex() {
//        return sex;
//    }
//
//    public void setSex(String sex) {
//        this.sex = sex;
//    }

    @Override
    public String toString() {
        return "{" +
                "\nname : " + name +
                "\nid : " + id +
                "\nsex : " + sex +
                "\npassword : " +  password +
                "\n}";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
