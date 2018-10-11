package com.vriera.productivity.utils;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.TaskService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;

public class DataBaseUtilsTest {

    @Mock
    private FileUtils fileUtils;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private PetitionService petitionService;
    @Mock
    private TaskService taskService;

    private DataBaseUtils dataBaseUtils;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dataBaseUtils = new DataBaseUtils(fileUtils, employeeService, petitionService, taskService);
    }

    @Test
    public void testCleanDB () {
        //When
        dataBaseUtils.cleanDB();

        //Then
        Mockito.verify(employeeService).deleteAll();
        Mockito.verify(petitionService).deleteAll();
        Mockito.verify(taskService).deleteAll();
    }

    @Test
    public void testLoadDB() throws IOException, ParseException {
        //Given
        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employeeService.loadFromFile("EMPLEADOS.xlsx")).thenReturn(Collections.singletonList(employee));

        File file = Mockito.mock(File.class);
        Mockito.when(file.getName()).thenReturn("PETICION_1");
        Mockito.when(fileUtils.getFiles()).thenReturn(Collections.singletonList(file));

        Petition petition = Mockito.mock(Petition.class);
        Mockito.when(petitionService.loadFromFile("PETICION_1")).thenReturn(petition);

        //When
        dataBaseUtils.loadDB();

        //Then
        Mockito.verify(employeeService).insert(employee);
        Mockito.verify(petitionService).insert(petition);
        Mockito.verify(taskService).loadFromFile(petition,"PETICION_1");
    }

}