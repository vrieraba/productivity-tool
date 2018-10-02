package com.vriera.productivity.tasks;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TaskBuilderTest {

    @Test
    public void testConstruction() {
        //Given
        TaskBuilder builder = new TaskBuilder();
        builder.petitionId(5);
        builder.employeeId(6);
        builder.taskType(TaskType.EVOLUTIVO);
        builder.taskSubType(TaskSubType.CODIFICACION);
        builder.estimated(25);
        builder.reported(45);

        //When
        Task actual = builder.build();

        //Then
        Assert.assertEquals(actual.getPetitionId(), (Integer) 5);
        Assert.assertEquals(actual.getEmployeeId(), (Integer) 6);
        Assert.assertEquals(actual.getTaskType(), TaskType.EVOLUTIVO);
        Assert.assertEquals(actual.getTaskSubType(), TaskSubType.CODIFICACION);
        Assert.assertEquals(actual.getTimeEstimated(), (Integer) 25);
        Assert.assertEquals(actual.getTimeReported(), (Integer) 45);
    }

}