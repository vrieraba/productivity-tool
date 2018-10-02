package com.vriera.productivity;

import com.vriera.productivity.controllers.report.EfficiencyReportController;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.reports.efficiency.EfficiencyReport;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class EfficiencyReportControllerTest {

    @Mock
    private EmployeeService employeeService;
    @Mock
    private EfficiencyService efficiencyService;

    private EfficiencyReportController efficiencyReportController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        efficiencyReportController = new EfficiencyReportController(employeeService, efficiencyService);
    }

    @Test
    public void testGetEfficiencyReport() {
        //Given
        Integer employeeId = 12345;

        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employeeService.getBy(employeeId)).thenReturn(employee);

        Mockito.when(efficiencyService.calculateGlobalEfficiency(employee)).thenReturn(2.5);
        Map<Month, Double> monthlyEfficiency = Mockito.mock(Map.class);
        Mockito.when(efficiencyService.calculateEfficiencyByMonth(employee)).thenReturn(monthlyEfficiency);


        //When
        EfficiencyReport actual = efficiencyReportController.getEfficiencyReport(employeeId);

        //Then
        Assert.assertEquals(actual.getEmployee(), employee);
        Assert.assertEquals(actual.getEfficiency(), 2.5);
        Assert.assertEquals(actual.getEfficiencyByMonth(), monthlyEfficiency);
    }

    @Test
    public void testGetEfficiencyReportWithoutEmployee() {
        //Given
        Mockito.when(efficiencyService.calculateGlobalEfficiency(null)).thenReturn(2.5);
        Map<Month, Double> monthlyEfficiency = Mockito.mock(Map.class);
        Mockito.when(efficiencyService.calculateEfficiencyByMonth(null)).thenReturn(monthlyEfficiency);

        //When
        EfficiencyReport actual = efficiencyReportController.getEfficiencyReport(null);

        //Then
        Mockito.verifyZeroInteractions(employeeService);
        Assert.assertNull(actual.getEmployee());
        Assert.assertEquals(actual.getEfficiency(), 2.5);
        Assert.assertEquals(actual.getEfficiencyByMonth(), monthlyEfficiency);
    }

}