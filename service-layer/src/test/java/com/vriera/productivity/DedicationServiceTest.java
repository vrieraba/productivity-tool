package com.vriera.productivity;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskService;
import com.vriera.productivity.tasks.TaskSubType;
import com.vriera.productivity.utils.MathUtils;
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
    private MathUtils mathUtils;

    private DedicationService dedicationService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dedicationService = new DedicationService(petitionService, taskService, mathUtils);
    }

    @Test
    public void testCalculateDedicationByTaskSubType() {
        //Given
        Employee employee = Mockito.mock(Employee.class);

        Petition petition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getAll()).thenReturn(Collections.singletonList(petition));

        Task analysisTask = Mockito.mock(Task.class);
        Mockito.when(analysisTask.getTimeReported()).thenReturn(2.0);
        Mockito.when(taskService.getBy(employee, petition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(analysisTask));
        Task developmentTask = Mockito.mock(Task.class);
        Mockito.when(developmentTask.getTimeReported()).thenReturn(5.0);
        Mockito.when(taskService.getBy(employee, petition, TaskSubType.CODIFICACION)).thenReturn(Collections.singletonList(developmentTask));

        Mockito.when(mathUtils.calculateDedication(Collections.singletonList(analysisTask))).thenReturn(2.00);
        Mockito.when(mathUtils.calculateDedication(Collections.singletonList(developmentTask))).thenReturn(5.00);

        //When
        Map<TaskSubType, Double> actual = dedicationService.calculateDedicationByTaskSubType(employee);

        //Then
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS), 2.00);
        Assert.assertEquals(actual.get(TaskSubType.CODIFICACION), 5.00);
        Assert.assertEquals(actual.get(TaskSubType.PRUEBA), 0.00);
    }

    @Test
    public void testCalculateDedicationByTaskSubTypeWithoutEmployee() {
        //Given
        Employee employee = null;

        Petition petition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getAll()).thenReturn(Collections.singletonList(petition));

        Task analysisTask = Mockito.mock(Task.class);
        Mockito.when(analysisTask.getTimeReported()).thenReturn(2.0);
        Mockito.when(taskService.getBy(employee, petition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(analysisTask));
        Task developmentTask = Mockito.mock(Task.class);
        Mockito.when(developmentTask.getTimeReported()).thenReturn(5.0);
        Mockito.when(taskService.getBy(employee, petition, TaskSubType.CODIFICACION)).thenReturn(Collections.singletonList(developmentTask));

        Mockito.when(mathUtils.calculateDedication(Collections.singletonList(analysisTask))).thenReturn(2.00);
        Mockito.when(mathUtils.calculateDedication(Collections.singletonList(developmentTask))).thenReturn(5.00);

        Mockito.when(mathUtils.calculateDifferentEmployees(Collections.singletonList(analysisTask))).thenReturn(3);
        Mockito.when(mathUtils.calculateDifferentEmployees(Collections.singletonList(developmentTask))).thenReturn(3);

        Mockito.when(mathUtils.round(0.6666666666666666, 2)).thenReturn(0.66);
        Mockito.when(mathUtils.round(1.6666666666666667, 2)).thenReturn(1.66);

        //When
        Map<TaskSubType, Double> actual = dedicationService.calculateDedicationByTaskSubType(employee);

        //Then
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS), 0.66);
        Assert.assertEquals(actual.get(TaskSubType.CODIFICACION), 1.66);
        Assert.assertEquals(actual.get(TaskSubType.PRUEBA), 0.00);
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
        Mockito.when(julyAnalysisTask.getTimeReported()).thenReturn(2.0);
        Mockito.when(taskService.getBy(employee, julyPetition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(julyAnalysisTask));
        Task augustAnalysisTask = Mockito.mock(Task.class);
        Mockito.when(augustAnalysisTask.getTimeReported()).thenReturn(5.0);
        Mockito.when(taskService.getBy(employee, augustPetition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(augustAnalysisTask));

        Mockito.when(mathUtils.calculateDedication(Collections.singletonList(julyAnalysisTask))).thenReturn(2.00);
        Mockito.when(mathUtils.calculateDedication(Collections.singletonList(augustAnalysisTask))).thenReturn(5.00);

        //When
        Map<TaskSubType, Map<Month, Double>> actual = dedicationService.calculateDedicationByMonthAndTaskSubType(employee);

        //Then
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS).get(Month.JULIO), 2.0);
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS).get(Month.AGOSTO), 5.0);
        Assert.assertNull(actual.get(TaskSubType.ANALISIS).get(Month.SEPTIEMBRE));
        Assert.assertEquals(actual.get(TaskSubType.CODIFICACION).get(Month.JULIO), 0.0);
    }

    @Test
    public void testCalculateDedicationByMonthAndTaskSubTypeWithoutEmployee() {
        //Given
        Employee employee = null;

        Mockito.when(petitionService.getMonthsWithPetitions()).thenReturn(Arrays.asList(Month.JULIO, Month.AGOSTO));

        Petition julyPetition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getBy(Month.JULIO)).thenReturn(Collections.singletonList(julyPetition));
        Petition augustPetition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getBy(Month.AGOSTO)).thenReturn(Collections.singletonList(augustPetition));

        Task julyAnalysisTask = Mockito.mock(Task.class);
        Mockito.when(julyAnalysisTask.getTimeReported()).thenReturn(2.0);
        Mockito.when(taskService.getBy(employee, julyPetition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(julyAnalysisTask));
        Task augustAnalysisTask = Mockito.mock(Task.class);
        Mockito.when(augustAnalysisTask.getTimeReported()).thenReturn(5.0);
        Mockito.when(taskService.getBy(employee, augustPetition, TaskSubType.ANALISIS)).thenReturn(Collections.singletonList(augustAnalysisTask));

        Mockito.when(mathUtils.calculateDedication(Collections.singletonList(julyAnalysisTask))).thenReturn(2.00);
        Mockito.when(mathUtils.calculateDedication(Collections.singletonList(augustAnalysisTask))).thenReturn(5.00);

        Mockito.when(mathUtils.calculateDifferentEmployees(Collections.singletonList(julyAnalysisTask))).thenReturn(2);
        Mockito.when(mathUtils.calculateDifferentEmployees(Collections.singletonList(augustAnalysisTask))).thenReturn(2);

        Mockito.when(mathUtils.round(1.0, 2)).thenReturn(1.00);
        Mockito.when(mathUtils.round(2.5, 2)).thenReturn(2.50);

        //When
        Map<TaskSubType, Map<Month, Double>> actual = dedicationService.calculateDedicationByMonthAndTaskSubType(employee);

        //Then
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS).get(Month.JULIO), 1.0);
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS).get(Month.AGOSTO), 2.5);
        Assert.assertNull(actual.get(TaskSubType.ANALISIS).get(Month.SEPTIEMBRE));
        Assert.assertEquals(actual.get(TaskSubType.CODIFICACION).get(Month.JULIO), 0.0);
    }

}