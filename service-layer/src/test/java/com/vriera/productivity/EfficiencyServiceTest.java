package com.vriera.productivity;

import com.vriera.productivity.employees.Employee;
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

public class EfficiencyServiceTest {

    @Mock
    private PetitionService petitionService;
    @Mock
    private TaskService taskService;
    @Mock
    private MathUtils mathUtils;

    private EfficiencyService efficiencyService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        efficiencyService = new EfficiencyService(petitionService, taskService, mathUtils);
    }

    @Test
    public void testCalculateGlobalEfficiency() {
        //Given
        Employee employee = Mockito.mock(Employee.class);

        Task effectiveTask = Mockito.mock(Task.class);
        Mockito.when(effectiveTask.getTaskSubType()).thenReturn(TaskSubType.CODIFICACION);
        Mockito.when(effectiveTask.getTimeReported()).thenReturn(5.0);
        Task nonEffectiveTask = Mockito.mock(Task.class);
        Mockito.when(nonEffectiveTask.getTaskSubType()).thenReturn(TaskSubType.GESTION);
        Mockito.when(nonEffectiveTask.getTimeReported()).thenReturn(3.0);

        Mockito.when(taskService.getBy(employee)).thenReturn(Arrays.asList(effectiveTask, nonEffectiveTask));

        Mockito.when(mathUtils.calculateEfficiency(Arrays.asList(effectiveTask, nonEffectiveTask), EfficiencyService.EFFECTIVE_TASK_SUB_TYPES)).thenReturn(62.50);

        //When
        double actual = efficiencyService.calculateGlobalEfficiency(employee);

        //Then
        Assert.assertEquals(actual, 62.50);
    }

    @Test
    public void testCalculateEfficiencyByMonth() {
        //Given
        Employee employee = Mockito.mock(Employee.class);

        Mockito.when(petitionService.getMonthsWithPetitions()).thenReturn(Collections.singletonList(Month.AGOSTO));

        Petition petition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.getBy(Month.AGOSTO)).thenReturn(Collections.singletonList(petition));

        Task effectiveTask = Mockito.mock(Task.class);
        Mockito.when(effectiveTask.getTaskSubType()).thenReturn(TaskSubType.CODIFICACION);
        Mockito.when(effectiveTask.getTimeReported()).thenReturn(5.0);
        Task nonEffectiveTask = Mockito.mock(Task.class);
        Mockito.when(nonEffectiveTask.getTaskSubType()).thenReturn(TaskSubType.GESTION);
        Mockito.when(nonEffectiveTask.getTimeReported()).thenReturn(3.0);

        Mockito.when(taskService.getBy(employee, petition)).thenReturn(Arrays.asList(effectiveTask, nonEffectiveTask));

        Mockito.when(mathUtils.calculateEfficiency(Arrays.asList(effectiveTask, nonEffectiveTask), EfficiencyService.EFFECTIVE_TASK_SUB_TYPES)).thenReturn(62.50);

        //When
        Map<Month, Double> actual = efficiencyService.calculateEfficiencyByMonth(employee);

        //Then
        Assert.assertEquals(actual.get(Month.AGOSTO), 62.50);
        Assert.assertNull(actual.get(Month.SEPTIEMBRE));
    }
}