package com.example.pracainzynierska.projects;

public class Project {
    private int projectID;
    private String documentID;
    private String projectName;
    private String projectZipCode;
    private String projectCity;
    private String projectStreet;
    private String projectHouseNumber;
    private String projectFlatNumber;
    private String projectConfine;
    private String projectPlotNumber;
    private String projectInvestorName;
    private String projectInvestorSurname;
    private boolean actualProject;

    public int getProjectID() {
        return projectID;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectZipCode() {
        return projectZipCode;
    }

    public void setProjectZipCode(String projectZipCode) {
        this.projectZipCode = projectZipCode;
    }

    public String getProjectCity() {
        return projectCity;
    }

    public void setProjectCity(String projectCity) {
        this.projectCity = projectCity;
    }

    public String getProjectStreet() {
        return projectStreet;
    }

    public void setProjectStreet(String projectStreet) {
        this.projectStreet = projectStreet;
    }

    public String getProjectHouseNumber() {
        return projectHouseNumber;
    }

    public void setProjectHouseNumber(String projectHouseNumber) {
        this.projectHouseNumber = projectHouseNumber;
    }

    public String getProjectFlatNumber() {
        return projectFlatNumber;
    }

    public void setProjectFlatNumber(String projectFlatNumber) {
        this.projectFlatNumber = projectFlatNumber;
    }

    public String getProjectConfine() {
        return projectConfine;
    }

    public void setProjectConfine(String projectConfine) {
        this.projectConfine = projectConfine;
    }

    public String getProjectPlotNumber() {
        return projectPlotNumber;
    }

    public void setProjectPlotNumber(String projectPlotNumber) {
        this.projectPlotNumber = projectPlotNumber;
    }

    public String getProjectInvestorName() {
        return projectInvestorName;
    }

    public void setProjectInvestorName(String projectInvestorName) {
        this.projectInvestorName = projectInvestorName;
    }

    public String getProjectInvestorSurname() {
        return projectInvestorSurname;
    }

    public void setProjectInvestorSurname(String projectInvestorSurname) {
        this.projectInvestorSurname = projectInvestorSurname;
    }

    public boolean isActualProject() {
        return actualProject;
    }

    public void setActualProject(boolean actualProject) {
        this.actualProject = actualProject;
    }
}
