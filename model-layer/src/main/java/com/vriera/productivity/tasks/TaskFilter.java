package com.vriera.productivity.tasks;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.Petition;

import java.util.List;

public class TaskFilter {

    private final Employee employee;
    private final List<Petition> petitions;
    private final List<TaskSubType> taskSubTypes;

    TaskFilter(TaskFilterBuilder builder) {
        employee = builder.getEmployee();
        petitions = builder.getPetitions();
        taskSubTypes = builder.getTaskSubTypes();
    }

    public Employee getEmployee() {
        return employee;
    }

    public List<Petition> getPetitions() {
        return petitions;
    }

    public List<TaskSubType> getTaskSubTypes() {
        return taskSubTypes;
    }
}
