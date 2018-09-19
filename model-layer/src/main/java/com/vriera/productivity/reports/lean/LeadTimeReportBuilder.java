package com.vriera.productivity.reports.lean;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.tasks.TaskSubType;

import java.util.Map;

public class LeadTimeReportBuilder {

    private Employee employee;
    private Map<Month, Double> leadTimeByMonth;
    private Map<TaskSubType, Map<Month, Double>> leadTimeByMonthAndTaskSubType;

    public LeadTimeReportBuilder employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public LeadTimeReportBuilder leadTimeByMonth(Map<Month, Double> leadTimeByMonth) {
        this.leadTimeByMonth = leadTimeByMonth;
        return this;
    }

    public LeadTimeReportBuilder leadTimeByMonthAndTaskSubType(Map<TaskSubType, Map<Month, Double>> leadTimeByMonthAndTaskSubType) {
        this.leadTimeByMonthAndTaskSubType = leadTimeByMonthAndTaskSubType;
        return this;
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

    public LeadTimeReport build() {
        return new LeadTimeReport(this);
    }

}
