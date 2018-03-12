package com.example.application.iiitdm;

/**
 * Created by S-Tech Computer on 20/07/2017.
 */

public class FacultyListItem  {
    private String facListname;
    private String facemail;
    private String facListdesignation;
    private String facdepartment;

    public FacultyListItem(String facListname, String facemail, String facListdesignation, String facdepartment) {
        this.facListname = facListname;
        this.facemail = facemail;
        this.facListdesignation = facListdesignation;
        this.facdepartment = facdepartment;
    }

    public String getFacListname() {
        return facListname;
    }

    public String getFacemail() {
        return facemail;
    }

    public String getFacListdesignation() {
        return facListdesignation;
    }

    public String getFacdepartment() {
        return facdepartment;
    }
    // private String facListImage;


}
