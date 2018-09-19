package com.vriera.productivity.reports;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.tasks.TaskSubType;

import java.util.List;

public class ReportFilterBuilder {

    private Employee employee;
    private List<TaskSubType> taskSubTypes;

    public ReportFilterBuilder employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public ReportFilterBuilder taskSubTypes(List<TaskSubType> taskSubTypes) {
        this.taskSubTypes = taskSubTypes;
        return this;
    }

    public Employee getEmployee() {
        return employee;
    }

    public List<TaskSubType> getTaskSubTypes() {
        return taskSubTypes;
    }

    public ReportFilter build() {
        return new ReportFilter(this);
    }

}
