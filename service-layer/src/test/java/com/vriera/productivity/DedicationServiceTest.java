package com.vriera.productivity;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
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
import java.util.Map;

public class DedicationServiceTest {

    @Mock
    private PetitionService petitionService;
    @Mock
    private TaskService taskService;
    @Mock
    private EmployeeService employeeService;

    private DedicationService dedicationService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dedicationService = new DedicationService(petitionService, taskService, employeeService);
    }

    @Test
    public void testCalculateDedicationByTaskSubType() {
        //Given
        Employee employee = Mockito.mock(Employee.class);

        Petition petition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getAll()).thenReturn(Collections.singletonList(petition));

        Task analysisTask = Mockito.mock(Task.class);
        Mockito.when(analysisTask.getTimeReported()).thenReturn(2);
        Mockito.when(taskService.getBy(employee, petition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(analysisTask));
        Task developmentTask = Mockito.mock(Task.class);
        Mockito.when(developmentTask.getTimeReported()).thenReturn(5);
        Mockito.when(taskService.getBy(employee, petition, TaskSubType.CODIFICACION)).thenReturn(Collections.singletonList(developmentTask));

        //When
        Map<TaskSubType, Double> actual = dedicationService.calculateDedicationByTaskSubType(employee);

        //Then
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS), 2.0);
        Assert.assertEquals(actual.get(TaskSubType.CODIFICACION), 5.0);
        Assert.assertEquals(actual.get(TaskSubType.PRUEBA), 0.0);
    }

    @Test
    public void testCalculateDedicationByTaskSubTypeWithoutEmployee() {
        //Given
        Employee employee = null;

        Petition petition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getAll()).thenReturn(Collections.singletonList(petition));

        Task analysisTask = Mockito.mock(Task.class);
        Mockito.when(analysisTask.getTimeReported()).thenReturn(2);
        Mockito.when(taskService.getBy(employee, petition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(analysisTask));
        Task developmentTask = Mockito.mock(Task.class);
        Mockito.when(developmentTask.getTimeReported()).thenReturn(5);
        Mockito.when(taskService.getBy(employee, petition, TaskSubType.CODIFICACION)).thenReturn(Collections.singletonList(developmentTask));

        Employee employee1 = Mockito.mock(Employee.class);
        Employee employee2 = Mockito.mock(Employee.class);
        Mockito.when(employeeService.getAll()).thenReturn(Arrays.asList(employee1, employee2));

        //When
        Map<TaskSubType, Double> actual = dedicationService.calculateDedicationByTaskSubType(employee);

        //Then
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS), 1.0);
        Assert.assertEquals(actual.get(TaskSubType.CODIFICACION), 2.5);
        Assert.assertEquals(actual.get(TaskSubType.PRUEBA), 0.0);
    }

    @Test
    public void testCalculateDedicationByMonthAndTaskSubType() {
        //Given
        Employee employee = Mockito.mock(Employee.class);

        Mockito.when(petitionService.getMonthsWithPetitions()).thenReturn(Arrays.asList(Month.JULIO, Month.AGOSTO));

        Petition julyPetition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getBy(Month.JULIO)).thenReturn(Collections.singletonList(julyPetition));
        Petition augustPetition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getBy(Month.AGOSTO)).thenReturn(Collections.singletonList(augustPetition));

        Task julyAnalysisTask = Mockito.mock(Task.class);
        Mockito.when(julyAnalysisTask.getTimeReported()).thenReturn(2);
        Mockito.when(taskService.getBy(employee, julyPetition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(julyAnalysisTask));
        Task augustAnalysisTask = Mockito.mock(Task.class);
        Mockito.when(augustAnalysisTask.getTimeReported()).thenReturn(5);
        Mockito.when(taskService.getBy(employee, augustPetition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(augustAnalysisTask));

        //When
        Map<TaskSubType, Map<Month, Double>> actual = dedicationService.calculateDedicationByMonthAndTaskSubType(employee);

        //Then
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS).get(Month.JULIO), 2.0);
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS).get(Month.AGOSTO), 5.0);
        Assert.assertNull(actual.get(TaskSubType.ANALISIS).get(Month.SEPTIEMBRE));
        Assert.assertEquals(actual.get(TaskSubType.CODIFICACION).get(Month.JULIO), 0.0);
    }

}