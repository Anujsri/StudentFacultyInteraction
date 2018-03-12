package com.example.application.iiitdm;

/**
 * Created by S-Tech Computer on 22/07/2017.
 */

public class StudentListItem {
  private  String studentListName;
    private String studentListEmail;
    private String studentListRoll;
    private String studentListDepartment;
    private String studenttListSkills;
    private String studenListproject;

    public StudentListItem(String studentListName, String studentListEmail, String studentListRoll, String studentListDepartment, String studenttListSkills, String studenListproject) {
        this.studentListName = studentListName;
        this.studentListEmail = studentListEmail;
        this.studentListRoll = studentListRoll;
        this.studentListDepartment = studentListDepartment;
        this.studenttListSkills = studenttListSkills;
        this.studenListproject = studenListproject;
    }

    public String getStudentListName() {
        return studentListName;
    }

    public String getStudentListEmail() {
        return studentListEmail;
    }

    public String getStudentListRoll() {
        return studentListRoll;
    }

    public String getStudentListDepartment() {
        return studentListDepartment;
    }

    public String getStudenttListSkills() {
        return studenttListSkills;
    }

    public String getStudenListproject() {
        return studenListproject;
    }
}
