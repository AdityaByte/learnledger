package com.learnledger.model;

public class ProjectsCard {

    private String projectHeading;
    private String projectSubHeading;

    public String getProjectHeading() {
        return projectHeading;
    }

    public void setProjectHeading(String projectHeading) {
        this.projectHeading = projectHeading;
    }

    public String getProjectSubHeading() {
        return projectSubHeading;
    }

    public void setProjectSubHeading(String projectSubHeading) {
        this.projectSubHeading = projectSubHeading;
    }

    @Override
    public String toString() {
        return "ProjectsCard{" + "projectHeading=" + projectHeading + ", projectSubHeading=" + projectSubHeading + '}';
    }
    
}
