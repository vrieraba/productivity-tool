package com.vriera.productivity.controllers.report;

import com.vriera.productivity.DedicationService;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.reports.dedication.DedicationReport;
import com.vriera.productivity.reports.dedication.DedicationReportBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dedication-report")
public class DedicationReportController {

    private final EmployeeService employeeService;
    private final DedicationService dedicationService;

    @Autowired
    public DedicationReportController(EmployeeService employeeService, DedicationService dedicationService) {
        this.employeeService = employeeService;
        this.dedicationService = dedicationService;
    }


    @GetMapping("/")
    public DedicationReport getDedicationReport(@RequestParam("employeeId") Integer employeeId) {
        Employee employee = null;
        if (employeeId != null) {
            employee = employeeService.getBy(employeeId);
        }
        DedicationReportBuilder builder = new DedicationReportBuilder();
        builder.employee(employee);
        builder.dedicationByTaskSubType(dedicationService.calculateDedicationByTaskSubType(employee));
        builder.dedicationByMonthAndTaskSubType(dedicationService.calculateDedicationByMonthAndTaskSubType(employee));
        return builder.build();
    }

}
