package com.vriera.productivity.utils;

import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskSubType;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by rieravictor on 3/10/18.
 */
public class MathUtilsTest {

    private MathUtils mathUtils;

    @BeforeMethod
    public void setUp() {
        mathUtils = new MathUtils();
    }

    @Test
    public void testRound() {
        //When
        Double actual = mathUtils.round(15.333333333, 2);
        //Then
        Assert.assertEquals(actual, 15.33);

    }

    @Test
    public void testCalculateProductivity() {
        //Given
        Task task1 = Mockito.mock(Task.class);
        Mockito.when(task1.getTimeReported()).thenReturn(5.5);
        Task task2 = Mockito.mock(Task.class);
        Mockito.when(task2.getTimeReported()).thenReturn(10.0);

        //When
        Double actual = mathUtils.calculateProductivity(Arrays.asList(task1, task2));

        //Then
        Assert.assertEquals(actual, 0.13);
    }

    @Test
    public void testCalculateProductivityWhenNoTasks() {
        //When
        Double actual = mathUtils.calculateProductivity(Collections.emptyList());

        //Then
        Assert.assertEquals(actual, 0.00);
    }

    @Test
    public void testCalculateEfficiency() {
        //Given
        Task task1 = Mockito.mock(Task.class);
        Mockito.when(task1.getTimeReported()).thenReturn(5.5);
        Mockito.when(task1.getTaskSubType()).thenReturn(TaskSubType.CODIFICACION);
        Task task2 = Mockito.mock(Task.class);
        Mockito.when(task2.getTimeReported()).thenReturn(10.0);
        Mockito.when(task2.getTaskSubType()).thenReturn(TaskSubType.GESTION);

        //When
        Double actual = mathUtils.calculateEfficiency(Arrays.asList(task1, task2), Collections.singletonList(TaskSubType.CODIFICACION));

        //Then
        Assert.assertEquals(actual, 35.48);
    }

    @Test
    public void testCalculateEfficiencyWithZeroReported() {
        //Given
        Task task1 = Mockito.mock(Task.class);
        Mockito.when(task1.getTimeReported()).thenReturn(0.0);
        Mockito.when(task1.getTaskSubType()).thenReturn(TaskSubType.GESTION);

        //When
        Double actual = mathUtils.calculateEfficiency(Arrays.asList(task1), Collections.singletonList(TaskSubType.CODIFICACION));

        //Then
        Assert.assertEquals(actual, 0.00);
    }

    @Test
    public void testCalculateDedication() {
        //Given
        Task task1 = Mockito.mock(Task.class);
        Mockito.when(task1.getTimeReported()).thenReturn(1.5);
        Task task2 = Mockito.mock(Task.class);
        Mockito.when(task2.getTimeReported()).thenReturn(5.0);
        Task task3 = Mockito.mock(Task.class);
        Mockito.when(task3.getTimeReported()).thenReturn(10.5);

        //When
        double actual = mathUtils.calculateDedication(Arrays.asList(task1, task2, task3));

        //Then
        Assert.assertEquals(actual, 17.00);
    }

    @Test
    public void testCalculateDifferentEmployees() {
        //Given
        Task task1 = Mockito.mock(Task.class);
        Mockito.when(task1.getEmployeeId()).thenReturn(1);
        Task task2 = Mockito.mock(Task.class);
        Mockito.when(task1.getEmployeeId()).thenReturn(2);
        Task task3 = Mockito.mock(Task.class);
        Mockito.when(task1.getEmployeeId()).thenReturn(1);

        //When
        int actual = mathUtils.calculateDifferentEmployees(Arrays.asList(task1, task2, task3));

        //Then
        Assert.assertEquals(actual, 2);
    }
}