package com.vriera.productivity.employees;

import org.junit.Test;
import org.testng.Assert;

public class EmployeeBuilderTest {

    @Test
    public void build() {
        //Given
        EmployeeBuilder employeeBuilder = new EmployeeBuilder();
        employeeBuilder.id(0);
        employeeBuilder.name("Peter");
        employeeBuilder.category(Category.CD);
        //When
        Employee employee = employeeBuilder.build();
        //Then
        Assert.assertEquals(employee.getId(), (Integer) 0);
        Assert.assertEquals(employee.getName(), "Peter");
        Assert.assertEquals(employee.getCategory(), Category.CD);
    }
}