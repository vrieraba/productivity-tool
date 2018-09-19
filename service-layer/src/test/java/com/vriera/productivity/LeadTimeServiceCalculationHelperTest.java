package com.vriera.productivity;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskService;
import com.vriera.productivity.tasks.TaskSubType;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LeadTimeServiceCalculationHelperTest {

    @Mock
    private PetitionService petitionService;
    @Mock
    private TaskService taskService;

    private LeadTimeServiceCalculationHelper leadTimeServiceCalculationHelper;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        leadTimeServiceCalculationHelper = new LeadTimeServiceCalculationHelper(petitionService, taskService);
    }

    @Test
    public void testGetLeadTimeTasks() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Month month = Month.AGOSTO;
        TaskSubType taskSubType = TaskSubType.CODIFICACION;

        Petition junePetition = Mockito.mock(Petition.class);
        Petition julyPetition = Mockito.mock(Petition.class);
        Petition augustPetition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getBy(Month.AGOSTO, 3)).thenReturn(Arrays.asList(junePetition, julyPetition, augustPetition));

        Task juneTask = Mockito.mock(Task.class);
        Mockito.when(taskService.getBy(employee, junePetition, taskSubType)).thenReturn(Arrays.asList(juneTask));
        Task julyTask = Mockito.mock(Task.class);
        Mockito.when(taskService.getBy(employee, julyPetition, taskSubType)).thenReturn(Arrays.asList(julyTask));
        Task augustTask = Mockito.mock(Task.class);
        Mockito.when(taskService.getBy(employee, augustPetition, taskSubType)).thenReturn(Arrays.asList(augustTask));

        //When
        List<Task> actual = leadTimeServiceCalculationHelper.getLeadTimeTasks(employee, month, taskSubType);

        //Then
        Assert.assertEquals(actual.get(0), juneTask);
        Assert.assertEquals(actual.get(1), julyTask);
        Assert.assertEquals(actual.get(2), augustTask);
    }

    @Test
    public void calculatePercentile() {
        //Given
        Task task1 = Mockito.mock(Task.class);
        Mockito.when(task1.getTimeReported()).thenReturn(15);
        Task task2 = Mockito.mock(Task.class);
        Mockito.when(task2.getTimeReported()).thenReturn(25);
        Task task3 = Mockito.mock(Task.class);
        Mockito.when(task3.getTimeReported()).thenReturn(25);
        Task task4 = Mockito.mock(Task.class);
        Mockito.when(task4.getTimeReported()).thenReturn(35);
        Task task5 = Mockito.mock(Task.class);
        Mockito.when(task5.getTimeReported()).thenReturn(65);
        //When
        double actual = leadTimeServiceCalculationHelper.calculatePercentile(Arrays.asList(task1, task2, task3, task4, task5), 85);
        //Then
        Assert.assertEquals(actual, 47.0);
    }

    @Test
    public void calculatePercentileWithoutTasks() {
        //When
        double actual = leadTimeServiceCalculationHelper.calculatePercentile(Collections.emptyList(), 85);
        //Then
        Assert.assertEquals(actual, 0.00);
    }

}