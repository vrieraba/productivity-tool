package com.vriera.productivity.reports;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.tasks.TaskSubType;

import java.util.List;

public class ReportFilter {

    private final Employee employee;
    private final List<TaskSubType> taskSubTypes;

    public ReportFilter(ReportFilterBuilder builder) {
        employee = builder.getEmployee();
        taskSubTypes = builder.getTaskSubTypes();
    }

    public Employee getEmployee() {
        return employee;
    }

    public List<TaskSubType> getTaskSubTypes() {
        return taskSubTypes;
    }
}
