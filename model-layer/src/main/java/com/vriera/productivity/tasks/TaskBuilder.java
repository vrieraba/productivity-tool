package com.vriera.productivity.tasks;

public class TaskBuilder {

    private Integer petitionId;
    private Integer employeeId;
    private String name;
    private TaskType taskType;
    private TaskSubType taskSubType;
    private Double estimated;
    private Double reported;

    public TaskBuilder petitionId(Integer petitionId) {
        this.petitionId = petitionId;
        return this;
    }

    public TaskBuilder employeeId(Integer employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public TaskBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TaskBuilder taskType(TaskType taskType) {
        this.taskType = taskType;
        return this;
    }

    public TaskBuilder taskSubType(TaskSubType taskSubType) {
        this.taskSubType = taskSubType;
        return this;
    }

    public TaskBuilder estimated(Double estimated) {
        this.estimated = estimated;
        return this;
    }
    public TaskBuilder reported(Double reported) {
        this.reported = reported;
        return this;
    }

    public Integer getPetitionId() {
        return petitionId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public TaskSubType getTaskSubType() {
        return taskSubType;
    }

    public Double getEstimated() {
        return estimated;
    }

    public Double getReported() {
        return reported;
    }

    public Task build() {
        return new Task(this);
    }
}

