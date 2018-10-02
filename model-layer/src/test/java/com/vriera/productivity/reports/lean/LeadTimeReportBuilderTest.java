package com.vriera.productivity.reports.lean;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.tasks.TaskSubType;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class LeadTimeReportBuilderTest {

    @Test
    public void testConstruction() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Map<Month, Double> leadTimeByMonth = Mockito.mock(Map.class);
        Map<TaskSubType, Map<Month, Double>> leadTimeByMonthAndTaskSubType = Mockito.mock(Map.class);

        LeadTimeReportBuilder builder = new LeadTimeReportBuilder();
        builder.employee(employee);
        builder.leadTimeByMonth(leadTimeByMonth);
        builder.leadTimeByMonthAndTaskSubType(leadTimeByMonthAndTaskSubType);

        //When
        LeadTimeReport actual = builder.build();

        //Then
        Assert.assertEquals(actual.getEmployee(), employee);
        Assert.assertEquals(actual.getLeadTimeByMonth(), leadTimeByMonth);
        Assert.assertEquals(actual.getLeadTimeByMonthAndTaskSubType(), leadTimeByMonthAndTaskSubType);
    }

}