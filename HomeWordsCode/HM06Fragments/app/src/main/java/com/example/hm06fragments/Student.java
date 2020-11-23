package com.example.hm06fragments;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String FirstName;
    private String LastName;
    private int imageUrl;
    private String Department;


    public Student() {

    }


    public Student(int id, String firstName, String lastName, int imageUrl, String department) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        this.imageUrl = imageUrl;
        Department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public String getDepartment() {
        return Department;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDepartment(String department) {
        Department = department;
    }
}
