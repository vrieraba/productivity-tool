package com.vriera.productivity.reports.dedication;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.tasks.TaskSubType;

import java.util.Map;

public class DedicationReportBuilder {

    private Employee employee;
    private Map<TaskSubType, Double> dedicationByTaskSubType;
    private Map<TaskSubType, Map<Month, Double>> dedicationByMonthAndTaskSubType;

    public DedicationReportBuilder employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public DedicationReportBuilder dedicationByTaskSubType(Map<TaskSubType, Double> dedicationByTaskSubType) {
        this.dedicationByTaskSubType = dedicationByTaskSubType;
        return this;
    }

    public DedicationReportBuilder dedicationByMonthAndTaskSubType(Map<TaskSubType, Map<Month, Double>> dedicationByMonthAndTaskSubType) {
        this.dedicationByMonthAndTaskSubType = dedicationByMonthAndTaskSubType;
        return this;
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

    public DedicationReport build() {
        return new DedicationReport(this);
    }

}
