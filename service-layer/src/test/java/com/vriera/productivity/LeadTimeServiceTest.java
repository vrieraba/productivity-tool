package com.vriera.productivity;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskSubType;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LeadTimeServiceTest {

    @Mock
    private LeadTimeServiceCalculationHelper calculationHelper;
    @Mock
    private PetitionService petitionService;

    private LeadTimeService leadTimeService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        leadTimeService = new LeadTimeService(calculationHelper, petitionService);
    }

    @Test
    public void testCalculateLeadTimeByMonth() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        List<Month> months = Collections.singletonList(Month.JULIO);
        Mockito.when(petitionService.getMonthsWithPetitions()).thenReturn(months);

        List<Task> analysisTasks = Collections.singletonList(Mockito.mock(Task.class));
        List<Task> codificationTasks = Collections.singletonList(Mockito.mock(Task.class));
        List<Task> qaTasks = Collections.singletonList(Mockito.mock(Task.class));
        List<Task> deployTasks = Collections.singletonList(Mockito.mock(Task.class));
        Mockito.when(calculationHelper.getLeadTimeTasks(employee, Month.JULIO, TaskSubType.ANALISIS)).thenReturn(analysisTasks);
        Mockito.when(calculationHelper.getLeadTimeTasks(employee, Month.JULIO, TaskSubType.CODIFICACION)).thenReturn(codificationTasks);
        Mockito.when(calculationHelper.getLeadTimeTasks(employee, Month.JULIO, TaskSubType.PRUEBA)).thenReturn(qaTasks);
        Mockito.when(calculationHelper.getLeadTimeTasks(employee, Month.JULIO, TaskSubType.DESPLIEGUE)).thenReturn(deployTasks);

        Mockito.when(calculationHelper.calculatePercentile(analysisTasks, 85)).thenReturn(10.0);
        Mockito.when(calculationHelper.calculatePercentile(codificationTasks, 85)).thenReturn(5.0);
        Mockito.when(calculationHelper.calculatePercentile(qaTasks, 85)).thenReturn(20.0);
        Mockito.when(calculationHelper.calculatePercentile(deployTasks, 85)).thenReturn(7.0);

        //When
        Map<Month, Double> actual = leadTimeService.calculateLeadTimeByMonth(employee);

        //Then
        Assert.assertEquals(actual.get(Month.JULIO), 42.0);
    }

    @Test
    public void testCalculateLeadTimeByMonthAndTaskSubType() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        List<Month> months = Collections.singletonList(Month.JULIO);
        Mockito.when(petitionService.getMonthsWithPetitions()).thenReturn(months);

        List<Task> analysisTasks = Collections.singletonList(Mockito.mock(Task.class));
        List<Task> codificationTasks = Collections.singletonList(Mockito.mock(Task.class));
        List<Task> qaTasks = Collections.singletonList(Mockito.mock(Task.class));
        List<Task> deployTasks = Collections.singletonList(Mockito.mock(Task.class));
        Mockito.when(calculationHelper.getLeadTimeTasks(employee, Month.JULIO, TaskSubType.ANALISIS)).thenReturn(analysisTasks);
        Mockito.when(calculationHelper.getLeadTimeTasks(employee, Month.JULIO, TaskSubType.CODIFICACION)).thenReturn(codificationTasks);
        Mockito.when(calculationHelper.getLeadTimeTasks(employee, Month.JULIO, TaskSubType.PRUEBA)).thenReturn(qaTasks);
        Mockito.when(calculationHelper.getLeadTimeTasks(employee, Month.JULIO, TaskSubType.DESPLIEGUE)).thenReturn(deployTasks);

        Mockito.when(calculationHelper.calculatePercentile(analysisTasks, 85)).thenReturn(10.0);
        Mockito.when(calculationHelper.calculatePercentile(codificationTasks, 85)).thenReturn(5.0);
        Mockito.when(calculationHelper.calculatePercentile(qaTasks, 85)).thenReturn(20.0);
        Mockito.when(calculationHelper.calculatePercentile(deployTasks, 85)).thenReturn(7.0);

        //When
        Map<TaskSubType ,Map<Month, Double>> actual = leadTimeService.calculateLeadTimeByMonthAndTaskSubType(employee);
        //Then
        Assert.assertEquals(actual.get(TaskSubType.ANALISIS).get(Month.JULIO), 10.0);
        Assert.assertEquals(actual.get(TaskSubType.CODIFICACION).get(Month.JULIO), 5.0);
        Assert.assertEquals(actual.get(TaskSubType.PRUEBA).get(Month.JULIO), 20.0);
        Assert.assertEquals(actual.get(TaskSubType.DESPLIEGUE).get(Month.JULIO), 7.0);
    }

}