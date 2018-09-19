package com.vriera.productivity.reports.efficiency;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;

import java.util.Map;

public class EfficiencyReportBuilder {

    private Employee employee;
    private Double efficiency;
    private Map<Month, Double> efficiencyByMonth;

    public EfficiencyReportBuilder employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public EfficiencyReportBuilder efficiency(Double efficiency) {
        this.efficiency = efficiency;
        return this;
    }

    public EfficiencyReportBuilder efficiencyByMonth(Map<Month, Double> monthlyEfficiency) {
        this.efficiencyByMonth = monthlyEfficiency;
        return this;
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

    public EfficiencyReport build() {
        return new EfficiencyReport(this);
    }

}
