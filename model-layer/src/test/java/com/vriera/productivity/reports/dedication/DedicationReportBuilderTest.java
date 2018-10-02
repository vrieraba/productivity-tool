package com.vriera.productivity.reports.dedication;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.tasks.TaskSubType;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class DedicationReportBuilderTest {

    @Test
    public void testConstruction() {
        Employee employee = Mockito.mock(Employee.class);
        Map<TaskSubType, Double> dedicationByTaskSubType = Mockito.mock(Map.class);
        Map<TaskSubType, Map<Month, Double>> dedicationByMonthAndTaskSubType = Mockito.mock(Map.class);

        DedicationReportBuilder builder = new DedicationReportBuilder();
        builder.employee(employee);
        builder.dedicationByTaskSubType(dedicationByTaskSubType);
        builder.dedicationByMonthAndTaskSubType(dedicationByMonthAndTaskSubType);

        //When
        DedicationReport actual = builder.build();

        //Then
        Assert.assertEquals(actual.getEmployee(), employee);
        Assert.assertEquals(actual.getDedicationByTaskSubType(), dedicationByTaskSubType);
        Assert.assertEquals(actual.getDedicationByMonthAndTaskSubType(), dedicationByMonthAndTaskSubType);
    }

}