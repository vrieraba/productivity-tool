package com.vriera.productivity.reports.productivity;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.tasks.TaskSubType;

import java.util.Map;

public class ProductivityReport {

    private final Employee employee;
    private final Double productivity;
    private final Map<Month, Double> productivityByMonth;
    private final Map<TaskSubType, Map<Month, Double>> productivityByMonthAndTaskSubType;

    public ProductivityReport(ProductivityReportBuilder builder) {
        employee = builder.getEmployee();
        productivity = builder.getProductivity();
        productivityByMonth = builder.getProductivityByMonth();
        productivityByMonthAndTaskSubType = builder.getProductivityByMonthAndTaskSubType();
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
}
