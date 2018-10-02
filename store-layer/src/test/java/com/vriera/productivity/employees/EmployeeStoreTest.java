package com.vriera.productivity.employees;

import com.vriera.productivity.Cache;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class EmployeeStoreTest {

    private EmployeeStore employeeStore;

    @BeforeMethod
    public void setUp() {
        Cache.employees.clear();
        employeeStore = new EmployeeStore();
    }

    @Test
    public void testDeleteAll() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Cache.employees.add(employee);

        //When
        employeeStore.deleteAll();

        //Then
        Assert.assertEquals(Cache.employees.size(), 0);
    }

    @Test
    public void testInsert() {
        //Given
        Employee employee = Mockito.mock(Employee.class);

        //When
        employeeStore.insert(employee);

        //Then
        Assert.assertEquals(Cache.employees.size(), 1);
        Assert.assertEquals(Cache.employees.get(0), employee);
    }

    @Test
    public void testGetAll() {
        //Given
        Employee employee1 = Mockito.mock(Employee.class);
        Employee employee2 = Mockito.mock(Employee.class);

        Cache.employees.addAll(Arrays.asList(employee1, employee2));

        //When
        List<Employee> actual = employeeStore.getAll();

        //Then
        Assert.assertEquals(actual.size(), 2);
        Assert.assertEquals(actual.get(0), employee1);
        Assert.assertEquals(actual.get(1), employee2);
    }

    @Test
    public void testGetByEmployee() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employee.getId()).thenReturn(12345);

        Cache.employees.add(employee);

        //When
        Employee actual = employeeStore.getEmployeeById(12345);

        //Then
        Assert.assertEquals(actual, employee);
    }

    @Test
    public void testGetByEmployeeWhenEmployeeDoesNotExists() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employee.getId()).thenReturn(1);

        Cache.employees.add(employee);

        //When
        Employee actual = employeeStore.getEmployeeById(2);

        //Then
        Assert.assertNull(actual);
    }
}