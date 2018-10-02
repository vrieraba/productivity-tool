package com.vriera.productivity.tasks;

import com.vriera.productivity.Cache;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.Petition;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TaskStoreTest {

    @BeforeMethod
    public void setUp() {
        Cache.tasks.clear();
    }

    @Test
    public void testDeleteAll() {
        //Given
        Task task = Mockito.mock(Task.class);
        Cache.tasks.add(task);

        //When
        new TaskStore().deleteAll();

        //Then
        Assert.assertEquals(Cache.tasks.size(), 0);
    }

    @Test
    public void testInsert() {
        //Given
        Task task = Mockito.mock(Task.class);

        //When
        new TaskStore().insert(task);

        //Then
        Assert.assertEquals(Cache.tasks.size(), 1);
        Assert.assertEquals(Cache.tasks.get(0), task);
    }

    @Test
    public void testGetAll() {
        //Given
        Task task1 = Mockito.mock(Task.class);
        Task task2 = Mockito.mock(Task.class);

        Cache.tasks.addAll(Arrays.asList(task1, task2));

        //When
        List<Task> actual = new TaskStore().getAll();

        //Then
        Assert.assertEquals(actual.size(), 2);
        Assert.assertEquals(actual.get(0), task1);
        Assert.assertEquals(actual.get(1), task2);
    }

    @Test
    public void testGetByEmployee() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employee.getId()).thenReturn(12345);

        Task task = Mockito.mock(Task.class);
        Mockito.when(task.getEmployeeId()).thenReturn(12345);
        Cache.tasks.add(task);

        //When
        List<Task> actual = new TaskStore().getTaskBy(employee, null, null);

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), task);
    }

    @Test
    public void testGetByEmployeeAndPetition() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employee.getId()).thenReturn(12345);

        Petition petition = Mockito.mock(Petition.class);
        Mockito.when(petition.getId()).thenReturn(1);

        Task task = Mockito.mock(Task.class);
        Mockito.when(task.getEmployeeId()).thenReturn(12345);
        Mockito.when(task.getPetitionId()).thenReturn(1);
        Cache.tasks.add(task);

        //When
        List<Task> actual = new TaskStore().getTaskBy(employee, petition, null);

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), task);
    }

    @Test
    public void testGetByEmployeeAndPetitionAnTaskSubType() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employee.getId()).thenReturn(12345);

        Petition petition = Mockito.mock(Petition.class);
        Mockito.when(petition.getId()).thenReturn(1);

        Task task = Mockito.mock(Task.class);
        Mockito.when(task.getEmployeeId()).thenReturn(12345);
        Mockito.when(task.getPetitionId()).thenReturn(1);
        Mockito.when(task.getTaskSubType()).thenReturn(TaskSubType.CODIFICACION);
        Cache.tasks.add(task);

        //When
        List<Task> actual = new TaskStore().getTaskBy(employee, petition, TaskSubType.CODIFICACION);

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), task);
    }

}