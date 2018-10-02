package com.vriera.productivity.controllers.report;

import com.vriera.productivity.EfficiencyService;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.reports.efficiency.EfficiencyReport;
import com.vriera.productivity.reports.efficiency.EfficiencyReportBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/efficiency-report")
public class EfficiencyReportController {

    private final EmployeeService employeeService;
    private final EfficiencyService efficiencyService;

    @Autowired
    public EfficiencyReportController(EmployeeService employeeService, EfficiencyService efficiencyService) {
        this.employeeService = employeeService;
        this.efficiencyService = efficiencyService;
    }

    @GetMapping("/")
    public EfficiencyReport getEfficiencyReport(@RequestParam("employeeId") Integer employeeId) {
        Employee employee = null;
        if (employeeId != null) {
            employee = employeeService.getBy(employeeId);
        }
        EfficiencyReportBuilder builder = new EfficiencyReportBuilder();
        builder.employee(employee);
        builder.efficiency(efficiencyService.calculateGlobalEfficiency(employee));
        builder.efficiencyByMonth(efficiencyService.calculateEfficiencyByMonth(employee));
        return builder.build();
    }
}
