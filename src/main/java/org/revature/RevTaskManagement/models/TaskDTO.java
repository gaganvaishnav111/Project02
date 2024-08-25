package org.revature.RevTaskManagement.models;

import java.util.Date;

public class TaskDTO {
    private String taskName;
    private String taskDetails;
    private Date startDate;
    private Date dueDate;
    private int projectId; // ID only
    private int assignedToId; // ID only
    private int milestoneId; // ID only

    public TaskDTO(){

    }

    public TaskDTO(String taskName, String taskDetails, Date startDate, Date dueDate, int projectId, int assignedToId, int milestoneId) {
        this.taskName = taskName;
        this.taskDetails = taskDetails;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.projectId = projectId;
        this.assignedToId = assignedToId;
        this.milestoneId = milestoneId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(int assignedToId) {
        this.assignedToId = assignedToId;
    }

    public int getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(int milestoneId) {
        this.milestoneId = milestoneId;
    }
    // Getters and Setters
}

