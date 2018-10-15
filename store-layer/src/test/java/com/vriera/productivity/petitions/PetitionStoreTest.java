package com.vriera.productivity.petitions;

import com.vriera.productivity.Cache;
import com.vriera.productivity.Month;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PetitionStoreTest {

    private PetitionStore petitionStore;

    @BeforeMethod
    public void setUp() {
        Cache.petitions.clear();
        petitionStore = new PetitionStore();
    }

    @Test
    public void testDeleteAll() {
        //Given
        Petition petition = Mockito.mock(Petition.class);
        Cache.petitions.add(petition);

        //When
        petitionStore.deleteAll();

        //Then
        Assert.assertEquals(Cache.petitions.size(), 0);
    }

    @Test
    public void testInsert() {
        //Given
        Petition petition = Mockito.mock(Petition.class);

        //When
        petitionStore.insert(petition);

        //Then
        Assert.assertEquals(Cache.petitions.size(), 1);
        Assert.assertEquals(Cache.petitions.get(0), petition);
    }

    @Test
    public void testGetAll() {
        //Given
        Petition petition1 = Mockito.mock(Petition.class);
        Petition petition2 = Mockito.mock(Petition.class);

        Cache.petitions.addAll(Arrays.asList(petition1, petition2));

        //When
        List<Petition> actual = petitionStore.getAll();

        //Then
        Assert.assertEquals(actual.size(), 2);
        Assert.assertEquals(actual.get(0), petition1);
        Assert.assertEquals(actual.get(1), petition2);
    }

    @Test
    public void testGetBy() {
        //Given
        Petition petition = Mockito.mock(Petition.class);
        Mockito.when(petition.getMonth()).thenReturn(Month.AGOSTO);

        Cache.petitions.add(petition);

        //When
        List<Petition> actual = petitionStore.getBy(Month.AGOSTO);

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), petition);
    }

    @Test
    public void testGetByWithoutMonth() {
        //Given
        Petition petition = Mockito.mock(Petition.class);

        Cache.petitions.add(petition);

        //When
        List<Petition> actual = petitionStore.getBy(null);

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), petition);
    }

    @Test
    public void testGetMonthsWithPetitions() {
        //Given
        Petition petition1 = Mockito.mock(Petition.class);
        Mockito.when(petition1.getMonth()).thenReturn(Month.AGOSTO);
        Petition petition2 = Mockito.mock(Petition.class);
        Mockito.when(petition2.getMonth()).thenReturn(Month.JULIO);

        Cache.petitions.addAll(Arrays.asList(petition1, petition2));

        //When
        List<Month> actual = petitionStore.getMonthsWithPetitions();

        //Then
        Assert.assertEquals(actual.size(), 2);
        Assert.assertEquals(actual.get(0), Month.AGOSTO);
        Assert.assertEquals(actual.get(1), Month.JULIO);
    }
}