package com.vriera.productivity.tasks;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.Petition;

import java.util.List;

public class TaskFilterBuilder {

    private Employee employee;
    private List<Petition> petitions;
    private List<TaskSubType> taskSubTypes;

    public TaskFilterBuilder() {
    }

    public TaskFilterBuilder employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public TaskFilterBuilder petitions(List<Petition> petitions) {
        this.petitions = petitions;
        return this;
    }

    public TaskFilterBuilder taskSubTypes(List<TaskSubType> taskSubTypes) {
        this.taskSubTypes = taskSubTypes;
        return this;
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

    public TaskFilter build() {
        return new TaskFilter(this);
    }
}
