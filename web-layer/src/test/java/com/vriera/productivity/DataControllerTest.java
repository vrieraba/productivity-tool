package com.vriera.productivity;

import com.vriera.productivity.controllers.DataController;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskService;
import com.vriera.productivity.tasks.TaskSubType;
import com.vriera.productivity.utils.DataBaseUtils;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataControllerTest {

    @Mock
    private DataBaseUtils dataBaseUtils;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private PetitionService petitionService;
    @Mock
    private TaskService taskService;

    private DataController dataController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dataController = new DataController(dataBaseUtils, employeeService, petitionService, taskService);
    }

    @Test
    public void testLoadData() throws IOException, ParseException {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employeeService.getAll()).thenReturn(Collections.singletonList(employee));
        Mockito.when(petitionService.getAll()).thenReturn(Collections.emptyList());
        Task task = Mockito.mock(Task.class);
        Mockito.when(taskService.getAll()).thenReturn(Arrays.asList(task, task));

        //When
        DataSummary actual = dataController.loadData();

        //Then
        Mockito.verify(dataBaseUtils).cleanDB();
        Mockito.verify(dataBaseUtils).loadDB();
        Assert.assertEquals(actual.getNumEmployees(), (Integer) 1);
        Assert.assertEquals(actual.getNumPetitions(), (Integer) 0);
        Assert.assertEquals(actual.getNumTasks(), (Integer) 2);
    }

    @Test
    public void testLoadDataWhenExceptionOccurs() throws IOException, ParseException {
        //Given
        Mockito.doThrow(new IOException("IOException")).when(dataBaseUtils).loadDB();

        Mockito.when(employeeService.getAll()).thenReturn(Collections.emptyList());
        Mockito.when(petitionService.getAll()).thenReturn(Collections.emptyList());
        Mockito.when(taskService.getAll()).thenReturn(Collections.emptyList());

        //When
        DataSummary actual = dataController.loadData();

        //Then
        Mockito.verify(dataBaseUtils).cleanDB();
        Assert.assertEquals(actual.getNumEmployees(), (Integer) 0);
        Assert.assertEquals(actual.getNumPetitions(), (Integer) 0);
        Assert.assertEquals(actual.getNumTasks(), (Integer) 0);
    }

    @Test
    public void testGetMonths() {
        //Given
        Month month = Month.AGOSTO;
        Mockito.when(petitionService.getMonthsWithPetitions()).thenReturn(Collections.singletonList(month));

        //When
        List<Month> actual = dataController.getMonths();

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual, Collections.singletonList(month));
    }

    @Test
    public void testGetTaskSubTypes() {
        //When
        List<TaskSubType> actual = dataController.getTaskSubTypes();

        //Then
        Assert.assertEquals(actual, Arrays.asList(TaskSubType.values()));
    }
}