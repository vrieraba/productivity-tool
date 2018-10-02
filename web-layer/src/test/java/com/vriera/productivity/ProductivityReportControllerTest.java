package com.vriera.productivity;

import com.vriera.productivity.controllers.report.ProductivityReportController;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.reports.productivity.ProductivityReport;
import com.vriera.productivity.tasks.TaskSubType;
import com.vriera.productivity.tasks.TaskType;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductivityReportControllerTest {

    @Mock
    private EmployeeService employeeService;
    @Mock
    private ProductivityService productivityService;

    private ProductivityReportController productivityReportController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productivityReportController = new ProductivityReportController(employeeService, productivityService);
    }

    @Test
    public void testGetProductivityReport() {
        //Given
        Integer employeeId = 12345;
        TaskType taskType = TaskType.EVOLUTIVO;

        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employeeService.getBy(employeeId)).thenReturn(employee);

        Mockito.when(productivityService.calculateGlobalProductivity(employee, taskType)).thenReturn(2.5);
        Map<Month, Double> productivityByMonth = Mockito.mock(Map.class);
        Mockito.when(productivityService.calculateProductivityByMonth(employee, taskType)).thenReturn(productivityByMonth);
        Map<TaskSubType, Map<Month, Double>> productivityByMonthAndTaskSubType = Mockito.mock(Map.class);
        Mockito.when(productivityService.calculateProductivityByMonthAndTaskSubType(employee, taskType)).thenReturn(productivityByMonthAndTaskSubType);


        //When
        ProductivityReport actual = productivityReportController.getProductivityReport(employeeId, taskType);

        //Then
        Assert.assertEquals(actual.getEmployee(), employee);
        Assert.assertEquals(actual.getProductivity(), 2.5);
        Assert.assertEquals(actual.getProductivityByMonth(), productivityByMonth);
        Assert.assertEquals(actual.getProductivityByMonthAndTaskSubType(), productivityByMonthAndTaskSubType);
    }

    @Test
    public void testGetProductivityReportWithoutEmployee() {
        //Given
        Integer employeeId = null;
        TaskType taskType = TaskType.EVOLUTIVO;

        Mockito.when(productivityService.calculateGlobalProductivity(null, taskType)).thenReturn(2.5);
        Map<Month, Double> productivityByMonth = Mockito.mock(Map.class);
        Mockito.when(productivityService.calculateProductivityByMonth(null, taskType)).thenReturn(productivityByMonth);
        Map<TaskSubType, Map<Month, Double>> productivityByMonthAndTaskSubType = Mockito.mock(Map.class);
        Mockito.when(productivityService.calculateProductivityByMonthAndTaskSubType(null, taskType)).thenReturn(productivityByMonthAndTaskSubType);


        //When
        ProductivityReport actual = productivityReportController.getProductivityReport(employeeId, taskType);

        //Then
        Mockito.verifyZeroInteractions(employeeService);
        Assert.assertNull(actual.getEmployee());
        Assert.assertEquals(actual.getProductivity(), 2.5);
        Assert.assertEquals(actual.getProductivityByMonth(), productivityByMonth);
        Assert.assertEquals(actual.getProductivityByMonthAndTaskSubType(), productivityByMonthAndTaskSubType);
    }

}