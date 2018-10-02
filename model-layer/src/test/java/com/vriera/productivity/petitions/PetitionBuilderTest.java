package com.vriera.productivity.petitions;


import com.vriera.productivity.Month;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetitionBuilderTest {

    @Test
    public void testConstruction() {
        //Given
        PetitionBuilder builder = new PetitionBuilder();
        builder.id(5);
        builder.month(Month.AGOSTO);
        builder.petitionType(PetitionType.EVOLUTIVO);

        //When
        Petition actual = builder.build();

        //Then
        Assert.assertEquals(actual.getId(), (Integer) 5);
        Assert.assertEquals(actual.getMonth(), Month.AGOSTO);
        Assert.assertEquals(actual.getPetitionType(), PetitionType.EVOLUTIVO);
    }

}