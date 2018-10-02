package com.vriera.productivity;

import com.vriera.productivity.controllers.report.LeadTimeReportController;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.reports.lean.LeadTimeReport;
import com.vriera.productivity.tasks.TaskSubType;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class LeadTimeReportControllerTest {
    @Mock
    private EmployeeService employeeService;
    @Mock
    private LeadTimeService leadTimeService;

    private LeadTimeReportController leadTimeReportController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        leadTimeReportController = new LeadTimeReportController(employeeService, leadTimeService);
    }

    @Test
    public void testGetLeadTimeReport() {
        //Given
        Integer employeeId = 12345;

        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employeeService.getBy(employeeId)).thenReturn(employee);

        Map<Month, Double> leadTimeByMonth = Mockito.mock(Map.class);
        Mockito.when(leadTimeService.calculateLeadTimeByMonth(employee)).thenReturn(leadTimeByMonth);
        Map<TaskSubType, Map<Month, Double>> leadTimeByMonthAndTaskSubType = Mockito.mock(Map.class);
        Mockito.when(leadTimeService.calculateLeadTimeByMonthAndTaskSubType(employee)).thenReturn(leadTimeByMonthAndTaskSubType);


        //When
        LeadTimeReport actual = leadTimeReportController.getLeadTimeReport(employeeId);

        //Then
        Assert.assertEquals(actual.getEmployee(), employee);
        Assert.assertEquals(actual.getLeadTimeByMonth(), leadTimeByMonth);
        Assert.assertEquals(actual.getLeadTimeByMonthAndTaskSubType(), leadTimeByMonthAndTaskSubType);
    }

    @Test
    public void testGetLeadTimeReportWithoutEmployee() {
        //Given
        Map<Month, Double> leadTimeByMonth = Mockito.mock(Map.class);
        Mockito.when(leadTimeService.calculateLeadTimeByMonth(null)).thenReturn(leadTimeByMonth);
        Map<TaskSubType, Map<Month, Double>> leadTimeByMonthAndTaskSubType = Mockito.mock(Map.class);
        Mockito.when(leadTimeService.calculateLeadTimeByMonthAndTaskSubType(null)).thenReturn(leadTimeByMonthAndTaskSubType);

        //When
        LeadTimeReport actual = leadTimeReportController.getLeadTimeReport(null);

        //Then
        Mockito.verifyZeroInteractions(employeeService);
        Assert.assertNull(actual.getEmployee());
        Assert.assertEquals(actual.getLeadTimeByMonth(), leadTimeByMonth);
        Assert.assertEquals(actual.getLeadTimeByMonthAndTaskSubType(), leadTimeByMonthAndTaskSubType);
    }
}