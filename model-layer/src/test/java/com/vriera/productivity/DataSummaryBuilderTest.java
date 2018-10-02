package com.vriera.productivity;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DataSummaryBuilderTest {

    @Test
    public void testConstruction() {
        //Given
        DataSummaryBuilder builder = new DataSummaryBuilder();
        builder.numEmployees(5);
        builder.numPetitions(25);
        builder.numTasks(514);

        //When
        DataSummary actual = builder.build();

        //Then
        Assert.assertEquals(actual.getNumEmployees(), (Integer) 5);
        Assert.assertEquals(actual.getNumPetitions(), (Integer) 25);
        Assert.assertEquals(actual.getNumTasks(), (Integer) 514);
    }

}