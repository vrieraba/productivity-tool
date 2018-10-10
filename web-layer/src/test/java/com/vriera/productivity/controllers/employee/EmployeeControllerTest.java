package com.vriera.productivity.controllers.employee;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    private EmployeeController employeeController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    public void testGetEmployees() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employeeService.getAll()).thenReturn(Collections.singletonList(employee));

        //When
        List<Employee> actual = employeeController.getEmployees();

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), employee);
    }
}