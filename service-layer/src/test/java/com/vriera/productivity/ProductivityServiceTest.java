package com.vriera.productivity;


import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskService;
import com.vriera.productivity.tasks.TaskSubType;
import com.vriera.productivity.tasks.TaskType;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class ProductivityServiceTest {

    @Mock
    private PetitionService petitionService;
    @Mock
    private TaskService taskService;

    private ProductivityService productivityService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productivityService = new ProductivityService(petitionService, taskService);
    }

    @Test
    public void testCalculateGlobalProductivity() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        TaskType taskType = TaskType.EVOLUTIVO;

        Petition petition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getAll()).thenReturn(Collections.singletonList(petition));

        Task analysisTask = Mockito.mock(Task.class);
        Mockito.when(analysisTask.getTimeReported()).thenReturn(5);
        Mockito.when(taskService.getBy(employee, petition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(analysisTask));

        Task developmentTask = Mockito.mock(Task.class);
        Mockito.when(developmentTask.getTimeReported()).thenReturn(1);
        Mockito.when(taskService.getBy(employee, petition, TaskSubType.CODIFICACION)).thenReturn(Collections.singletonList(developmentTask));

        //When
        Double actual = productivityService.calculateGlobalProductivity(employee, taskType);

        //Then
        Assert.assertEquals(actual, 33.33);
    }

    @Test
    public void testCalculateProductivityByMonth() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        TaskType taskType = TaskType.EVOLUTIVO;

        Mockito.when(petitionService.getMonthsWithPetitions()).thenReturn(Arrays.asList(Month.JULIO, Month.AGOSTO));

        Petition julyPetition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getBy(Month.JULIO)).thenReturn(Collections.singletonList(julyPetition));
        Petition augustPetition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getBy(Month.AGOSTO)).thenReturn(Collections.singletonList(augustPetition));

        Task julyAnalysisTask = Mockito.mock(Task.class);
        Mockito.when(julyAnalysisTask.getTimeReported()).thenReturn(5);
        Mockito.when(taskService.getBy(employee, julyPetition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(julyAnalysisTask));

        Task augustAnalysisTask = Mockito.mock(Task.class);
        Mockito.when(augustAnalysisTask.getTimeReported()).thenReturn(1);
        Mockito.when(taskService.getBy(employee, augustPetition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(augustAnalysisTask));

        //When
        Map<Month, Double> actual = productivityService.calculateProductivityByMonth(employee, taskType);

        //Then
        Assert.assertEquals(actual.get(Month.JULIO), 20.00);
        Assert.assertEquals(actual.get(Month.AGOSTO), 100.00);
        Assert.assertNull(actual.get(Month.SEPTIEMBRE));
    }

    @Test
    public void testCalculateProductivityByMonthAndTaskSubType() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        TaskType taskType = TaskType.EVOLUTIVO;

        Mockito.when(petitionService.getMonthsWithPetitions()).thenReturn(Arrays.asList(Month.JULIO, Month.AGOSTO));

        Petition julyPetition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getBy(Month.JULIO)).thenReturn(Collections.singletonList(julyPetition));
        Petition augustPetition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getBy(Month.AGOSTO)).thenReturn(Collections.singletonList(augustPetition));

        Task julyAnalysisTask = Mockito.mock(Task.class);
        Mockito.when(julyAnalysisTask.getTimeReported()).thenReturn(5);
        Mockito.when(taskService.getBy(employee, julyPetition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(julyAnalysisTask));

        Task augustAnalysisTask = Mockito.mock(Task.class);
        Mockito.when(augustAnalysisTask.getTimeReported()).thenReturn(1);
        Mockito.when(taskService.getBy(employee, augustPetition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(augustAnalysisTask));

        //When
        Map<TaskSubType, Map<Month, Double>> actual = productivityService.calculateProductivityByMonthAndTaskSubType(employee, taskType);

        //Then
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS).get(Month.JULIO), 20.00);
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS).get(Month.AGOSTO), 100.00);
        Assert.assertNull(actual.get(TaskSubType.ANALISIS).get(Month.SEPTIEMBRE));
        Assert.assertEquals(actual.get(TaskSubType.CODIFICACION).get(Month.JULIO), 0.00);
        Assert.assertEquals(actual.get(TaskSubType.CODIFICACION).get(Month.AGOSTO), 0.00);
        Assert.assertNull(actual.get(TaskSubType.CODIFICACION).get(Month.SEPTIEMBRE));
    }
}