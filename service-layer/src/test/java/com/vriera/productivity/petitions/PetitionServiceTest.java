package com.vriera.productivity.petitions;

import com.vriera.productivity.Month;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PetitionServiceTest {

    @Mock
    private PetitionStore petitionStore;

    private PetitionService petitionService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        petitionService = new PetitionService(petitionStore);
    }

    @Test
    public void testLoadFromFile() {
        //Given
        String petitionName = "PETICION_12345_EVOLUTIVO_AGOSTO.xlsx";

        //When
        Petition actual = petitionService.loadFromFile(petitionName);

        //Then
        Assert.assertEquals(actual.getId(), (Integer) 12345);
        Assert.assertEquals(actual.getMonth(), Month.AGOSTO);
        Assert.assertEquals(actual.getPetitionType(), PetitionType.EVOLUTIVO);
    }

    @Test
    public void testDeleteAll() {
        //When
        petitionService.deleteAll();

        //Then
        Mockito.verify(petitionStore).deleteAll();
    }

    @Test
    public void testInsert() {
        //Given
        Petition petition = Mockito.mock(Petition.class);

        //When
        petitionService.insert(petition);

        //Then
        Mockito.verify(petitionStore).insert(petition);
    }

    @Test
    public void testGetAll() {
        //When
        petitionService.getAll();

        //Then
        Mockito.verify(petitionStore).getAll();
    }

    @Test
    public void testGetByMonth() {
        //When
        petitionService.getBy(Month.AGOSTO);

        //Then
        Mockito.verify(petitionStore).getBy(Month.AGOSTO);
    }

    @Test
    public void testGetByMonthWithTimeWindow() {
        //When
        petitionService.getBy(Month.AGOSTO, 3);

        //Then
        Mockito.verify(petitionStore).getBy(Month.AGOSTO);
        Mockito.verify(petitionStore).getBy(Month.JULIO);
        Mockito.verify(petitionStore).getBy(Month.JUNIO);
        Mockito.verifyNoMoreInteractions(petitionStore);
    }

    @Test
    public void testGetMonthsWithPetitions() {
        //When
        petitionService.getMonthsWithPetitions();

        //Then
        Mockito.verify(petitionStore).getMonthsWithPetitions();
    }
}