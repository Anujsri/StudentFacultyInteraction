package com.example.application.iiitdm;

/**
 * Created by S-Tech Computer on 22/07/2017.
 */

public class ProjectListItem {
    private String projectListName;
    private String projectListDescription;

    public ProjectListItem(String projectListName, String projectListDescription) {
        this.projectListName = projectListName;
        this.projectListDescription = projectListDescription;
    }

    public String getProjectListName() {
        return projectListName;
    }

    public String getProjectListDescription() {
        return projectListDescription;
    }
}
