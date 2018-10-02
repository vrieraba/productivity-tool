package com.vriera.productivity.reports.productivity;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.tasks.TaskSubType;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductivityReportBuilderTest {

    @Test
    public void testConstruction() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Map<Month, Double> productivityByMonth = Mockito.mock(Map.class);
        Map<TaskSubType, Map<Month, Double>> productivityByMonthAndTaskSubType = Mockito.mock(Map.class);

        ProductivityReportBuilder builder = new ProductivityReportBuilder();
        builder.employee(employee);
        builder.productivity(2.5);
        builder.productivityByMonth(productivityByMonth);
        builder.productivityByMonthAndTaskSubType(productivityByMonthAndTaskSubType);

        //When
        ProductivityReport actual = builder.build();

        //Then
        Assert.assertEquals(actual.getEmployee(), employee);
        Assert.assertEquals(actual.getProductivity(), 2.5);
        Assert.assertEquals(actual.getProductivityByMonth(), productivityByMonth);
        Assert.assertEquals(actual.getProductivityByMonthAndTaskSubType(), productivityByMonthAndTaskSubType);
    }

}