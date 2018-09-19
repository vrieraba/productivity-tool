package com.vriera.productivity.reports.lean;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.tasks.TaskSubType;

import java.util.Map;

public class LeadTimeReport {

    private final Employee employee;
    private final Map<Month, Double> leadTimeByMonth;
    private final Map<TaskSubType, Map<Month, Double>> leadTimeByMonthAndTaskSubType;

    LeadTimeReport(LeadTimeReportBuilder builder) {
        employee = builder.getEmployee();
        leadTimeByMonth = builder.getLeadTimeByMonth();
        leadTimeByMonthAndTaskSubType = builder.getLeadTimeByMonthAndTaskSubType();
    }

    public Employee getEmployee() {
        return employee;
    }

    public Map<Month, Double> getLeadTimeByMonth() {
        return leadTimeByMonth;
    }

    public Map<TaskSubType, Map<Month, Double>> getLeadTimeByMonthAndTaskSubType() {
        return leadTimeByMonthAndTaskSubType;
    }
}
