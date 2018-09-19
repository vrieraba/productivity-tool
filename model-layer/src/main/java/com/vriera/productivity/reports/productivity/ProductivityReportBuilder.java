package com.vriera.productivity.reports.productivity;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.tasks.TaskSubType;

import java.util.Map;

public class ProductivityReportBuilder {

    private Employee employee;
    private Double productivity;
    private Map<Month, Double> productivityByMonth;
    private Map<TaskSubType, Map<Month, Double>> productivityByMonthAndTaskSubType;

    public ProductivityReportBuilder employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public ProductivityReportBuilder productivity(Double productivity) {
        this.productivity = productivity;
        return this;
    }

    public ProductivityReportBuilder productivityByMonth(Map<Month, Double> productivityByMonth) {
        this.productivityByMonth = productivityByMonth;
        return this;
    }

    public ProductivityReportBuilder productivityByMonthAndTaskSubType(Map<TaskSubType, Map<Month, Double>> productivityByMonthAndTaskSubType) {
        this.productivityByMonthAndTaskSubType = productivityByMonthAndTaskSubType;
        return this;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Double getProductivity() {
        return productivity;
    }

    public Map<Month, Double> getProductivityByMonth() {
        return productivityByMonth;
    }

    public Map<TaskSubType, Map<Month, Double>> getProductivityByMonthAndTaskSubType() {
        return productivityByMonthAndTaskSubType;
    }

    public ProductivityReport build() {
        return new ProductivityReport(this);
    }


}
