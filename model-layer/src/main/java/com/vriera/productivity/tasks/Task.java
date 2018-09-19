package com.vriera.productivity.tasks;

public class Task {

    private final Integer petitionId;
    private final Integer employeeId;
    private final TaskType taskType;
    private final TaskSubType taskSubType;
    private final Integer timeEstimated;
    private final Integer timeReported;

    Task(TaskBuilder builder) {
        petitionId = builder.getPetitionId();
        employeeId = builder.getEmployeeId();
        taskType = builder.getTaskType();
        taskSubType = builder.getTaskSubType();
        timeEstimated = builder.getEstimated();
        timeReported = builder.getReported();
    }

    public Integer getPetitionId() {
        return petitionId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public TaskSubType getTaskSubType() {
        return taskSubType;
    }

    public Integer getTimeEstimated() {
        return timeEstimated;
    }

    public Integer getTimeReported() {
        return timeReported;
    }

}
