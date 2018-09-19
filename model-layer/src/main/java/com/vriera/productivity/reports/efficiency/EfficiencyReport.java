package com.vriera.productivity.reports.efficiency;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;

import java.util.Map;

public class EfficiencyReport {

    private final Employee employee;
    private final Double efficiency;
    private Map<Month, Double> efficiencyByMonth;

    EfficiencyReport(EfficiencyReportBuilder builder) {
        employee = builder.getEmployee();
        efficiency = builder.getEfficiency();
        efficiencyByMonth = builder.getEfficiencyByMonth();
    }

    public Employee getEmployee() {
        return employee;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public Map<Month, Double> getEfficiencyByMonth() {
        return efficiencyByMonth;
    }
}
