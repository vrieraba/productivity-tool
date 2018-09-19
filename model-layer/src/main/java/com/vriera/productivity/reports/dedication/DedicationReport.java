package com.vriera.productivity.reports.dedication;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.tasks.TaskSubType;

import java.util.Map;

public class DedicationReport {

    private final Employee employee;
    private final Map<TaskSubType, Double> dedicationByTaskSubType;
    private final Map<TaskSubType, Map<Month, Double>> dedicationByMonthAndTaskSubType;

    public DedicationReport(DedicationReportBuilder builder) {
        employee = builder.getEmployee();
        dedicationByTaskSubType = builder.getDedicationByTaskSubType();
        dedicationByMonthAndTaskSubType = builder.getDedicationByMonthAndTaskSubType();
    }

    public Employee getEmployee() {
        return employee;
    }

    public Map<TaskSubType, Double> getDedicationByTaskSubType() {
        return dedicationByTaskSubType;
    }

    public Map<TaskSubType, Map<Month, Double>> getDedicationByMonthAndTaskSubType() {
        return dedicationByMonthAndTaskSubType;
    }
}
