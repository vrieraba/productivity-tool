package com.vriera.productivity;

import com.vriera.productivity.controllers.report.DedicationReportController;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.reports.dedication.DedicationReport;
import com.vriera.productivity.tasks.TaskSubType;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class DedicationReportControllerTest {

    @Mock
    private EmployeeService employeeService;
    @Mock
    private DedicationService dedicationService;

    private DedicationReportController dedicationReportController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dedicationReportController = new DedicationReportController(employeeService, dedicationService);
    }

    @Test
    public void testGetDedicationReport() {
        //Given
        Integer employeeId = 12345;

        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employeeService.getBy(employeeId)).thenReturn(employee);

        Map<TaskSubType, Double> dedicationByTaskSubType = Mockito.mock(Map.class);
        Mockito.when(dedicationService.calculateDedicationByTaskSubType(employee)).thenReturn(dedicationByTaskSubType);
        Map<TaskSubType, Map<Month, Double>> dedicationByMonthAndTaskSubType = Mockito.mock(Map.class);
        Mockito.when(dedicationService.calculateDedicationByMonthAndTaskSubType(employee)).thenReturn(dedicationByMonthAndTaskSubType);


        //When
        DedicationReport actual = dedicationReportController.getDedicationReport(employeeId);

        //Then
        Assert.assertEquals(actual.getEmployee(), employee);
        Assert.assertEquals(actual.getDedicationByTaskSubType(), dedicationByTaskSubType);
        Assert.assertEquals(actual.getDedicationByMonthAndTaskSubType(), dedicationByMonthAndTaskSubType);
    }

    @Test
    public void testGetDedicationReportWithoutEmployee() {
        //Given
        Map<TaskSubType, Double> dedicationByTaskSubType = Mockito.mock(Map.class);
        Mockito.when(dedicationService.calculateDedicationByTaskSubType(null)).thenReturn(dedicationByTaskSubType);
        Map<TaskSubType, Map<Month, Double>> dedicationByMonthAndTaskSubType = Mockito.mock(Map.class);
        Mockito.when(dedicationService.calculateDedicationByMonthAndTaskSubType(null)).thenReturn(dedicationByMonthAndTaskSubType);

        //When
        DedicationReport actual = dedicationReportController.getDedicationReport(null);

        //Then
        Mockito.verifyZeroInteractions(employeeService);
        Assert.assertNull(actual.getEmployee());
        Assert.assertEquals(actual.getDedicationByTaskSubType(), dedicationByTaskSubType);
        Assert.assertEquals(actual.getDedicationByMonthAndTaskSubType(), dedicationByMonthAndTaskSubType);
    }

}