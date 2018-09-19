package com.vriera.productivity;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.reports.dedication.DedicationReport;
import com.vriera.productivity.reports.dedication.DedicationReportBuilder;
import com.vriera.productivity.reports.efficiency.EfficiencyReport;
import com.vriera.productivity.reports.efficiency.EfficiencyReportBuilder;
import com.vriera.productivity.reports.lean.LeadTimeReport;
import com.vriera.productivity.reports.lean.LeadTimeReportBuilder;
import com.vriera.productivity.reports.productivity.ProductivityReport;
import com.vriera.productivity.reports.productivity.ProductivityReportBuilder;
import com.vriera.productivity.tasks.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProductivityService productivityService;
    @Autowired
    private EfficiencyService efficiencyService;
    @Autowired
    private DedicationService dedicationService;
    @Autowired
    private LeadTimeService leadTimeService;

    @GetMapping("/productivity")
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

    @GetMapping("/efficiency")
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

    @GetMapping("/dedication")
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

    @GetMapping("/leadTime")
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
