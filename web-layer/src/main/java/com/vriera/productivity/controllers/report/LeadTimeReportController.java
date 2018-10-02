package com.vriera.productivity.controllers.report;

import com.vriera.productivity.LeadTimeService;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.reports.lean.LeadTimeReport;
import com.vriera.productivity.reports.lean.LeadTimeReportBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lead-time-report")
public class LeadTimeReportController {

    private final EmployeeService employeeService;
    private final LeadTimeService leadTimeService;

    @Autowired
    public LeadTimeReportController(EmployeeService employeeService, LeadTimeService leadTimeService) {
        this.employeeService = employeeService;
        this.leadTimeService = leadTimeService;
    }

    @GetMapping("/")
    public LeadTimeReport getLeadTimeReport(@RequestParam("employeeId") Integer employeeId) {
        Employee employee = null;
        if (employeeId != null) {
            employee = employeeService.getBy(employeeId);
        }
        LeadTimeReportBuilder builder = new LeadTimeReportBuilder();
        builder.employee(employee);
        builder.leadTimeByMonth(leadTimeService.calculateLeadTimeByMonth(employee));
        builder.leadTimeByMonthAndTaskSubType(leadTimeService.calculateLeadTimeByMonthAndTaskSubType(employee));
        return builder.build();
    }

}
