package com.vriera.productivity.reports.efficiency;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class EfficiencyReportBuilderTest {

    @Test
    public void testConstruction() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Map<Month, Double> efficiencyByMonth = Mockito.mock(Map.class);

        EfficiencyReportBuilder builder = new EfficiencyReportBuilder();
        builder.employee(employee);
        builder.efficiency(2.5);
        builder.efficiencyByMonth(efficiencyByMonth);

        //When
        EfficiencyReport actual = builder.build();

        //Then
        Assert.assertEquals(actual.getEmployee(), employee);
        Assert.assertEquals(actual.getEfficiency(), 2.5);
        Assert.assertEquals(actual.getEfficiencyByMonth(), efficiencyByMonth);
    }

}