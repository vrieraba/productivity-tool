package com.vriera.productivity.controllers.report;

import com.vriera.productivity.ProductivityService;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.reports.productivity.ProductivityReport;
import com.vriera.productivity.reports.productivity.ProductivityReportBuilder;
import com.vriera.productivity.tasks.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productivity-report")
public class ProductivityReportController {

    private final EmployeeService employeeService;
    private final ProductivityService productivityService;

    @Autowired
    public ProductivityReportController(EmployeeService employeeService, ProductivityService productivityService) {
        this.employeeService = employeeService;
        this.productivityService = productivityService;
    }

    @GetMapping("/")
    public ProductivityReport getProductivityReport(@RequestParam("employeeId") Integer employeeId, @RequestParam("serviceType") TaskType taskType) {
        Employee employee = null;
        if (employeeId != null) {
            employee = employeeService.getBy(employeeId);
        }
        ProductivityReportBuilder builder = new ProductivityReportBuilder();
        builder.employee(employee);
        builder.productivity(productivityService.calculateGlobalProductivity(employee, taskType));
        builder.productivityByMonth(productivityService.calculateProductivityByMonth(employee, taskType));
        builder.productivityByMonthAndTaskSubType(productivityService.calculateProductivityByMonthAndTaskSubType(employee, taskType));
        return builder.build();
    }

}
