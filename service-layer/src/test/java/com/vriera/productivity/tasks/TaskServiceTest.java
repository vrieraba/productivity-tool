package com.vriera.productivity.tasks;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.utils.ExcelUtils;
import com.vriera.productivity.utils.FileUtils;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskServiceTest {


    @Mock
    private TaskStore taskStore;
    @Mock
    private ExcelUtils excelUtils;
    @Mock
    private FileUtils fileUtils;
    @Mock
    private EmployeeService employeeService;

    private TaskService taskService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        taskService = new TaskService(taskStore, excelUtils, fileUtils, employeeService);
    }

    @Test
    public void testLoadFromFile() throws IOException {
        //Given
        Petition petition = Mockito.mock(Petition.class);
        Mockito.when(petition.getId()).thenReturn(1);
        String fileName = "prueba.xls";

        InputStream inputStream = Mockito.mock(InputStream.class);
        Mockito.when(fileUtils.getInputSteam(fileName)).thenReturn(inputStream);

        Map<String, String> map = new HashMap<>();
        map.put("Responsable", "12345");
        map.put("Tipo", "Evolutivo (ENP)");
        map.put("Subtipo", "CODIFICACION");
        map.put("Esfuerzo", "5");
        map.put("Incurrido", "6");
        Mockito.when(excelUtils.readFile(inputStream)).thenReturn(Collections.singletonList(map));

        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employeeService.getBy(12345)).thenReturn(employee);

        //When
        List<Task> actual = taskService.loadFromFile(petition, fileName);

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0).getPetitionId(), (Integer) 1);
        Assert.assertEquals(actual.get(0).getEmployeeId(), (Integer) 12345);
        Assert.assertEquals(actual.get(0).getTaskType(), TaskType.EVOLUTIVO);
        Assert.assertEquals(actual.get(0).getTaskSubType(), TaskSubType.CODIFICACION);
        Assert.assertEquals(actual.get(0).getTimeEstimated(), (Integer) 5);
        Assert.assertEquals(actual.get(0).getTimeReported(), (Integer) 6);
    }

    @Test
    public void testDeleteAll() {
        //When
        taskService.deleteAll();

        //Then
        Mockito.verify(taskStore).deleteAll();
    }

    @Test
    public void testGetAll() {
        //When
        taskService.getAll();

        //Then
        Mockito.verify(taskStore).getAll();
    }

    @Test
    public void testGetByEmployee() {
        //Given
        Employee employee = Mockito.mock(Employee.class);

        Task task = Mockito.mock(Task.class);
        Mockito.when(taskStore.getTaskBy(employee, null, null)).thenReturn(Collections.singletonList(task));

        //When
        List<Task> actual = taskService.getBy(employee);

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), task);
    }

    @Test
    public void testGetByEmployeeAndPetition() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Petition petition = Mockito.mock(Petition.class);

        Task task = Mockito.mock(Task.class);
        Mockito.when(taskStore.getTaskBy(employee, petition, null)).thenReturn(Collections.singletonList(task));

        //When
        List<Task> actual = taskService.getBy(employee, petition);

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), task);
    }

    @Test
    public void testGetByEmployeeAndPetitionAndTaskSubType() {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Petition petition = Mockito.mock(Petition.class);
        TaskSubType taskSubType = TaskSubType.CODIFICACION;

        Task task = Mockito.mock(Task.class);
        Mockito.when(taskStore.getTaskBy(employee, petition, taskSubType)).thenReturn(Collections.singletonList(task));

        //When
        List<Task> actual = taskService.getBy(employee, petition, taskSubType);

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0), task);
    }
}