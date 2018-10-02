package com.vriera.productivity.employees;

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

public class EmployeeServiceTest {

    @Mock
    private FileUtils fileUtils;
    @Mock
    private ExcelUtils excelUtils;
    @Mock
    private EmployeeStore employeeStore;

    private EmployeeService employeeService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeService(fileUtils, excelUtils, employeeStore);
    }

    @Test
    public void testLoadFromFile() throws IOException {
        //Given
        String fileName = "prueba.xls";

        InputStream inputStream = Mockito.mock(InputStream.class);
        Mockito.when(fileUtils.getInputSteam(fileName)).thenReturn(inputStream);

        Map<String, String> map = new HashMap<>();
        map.put("NUMERO", "1");
        map.put("NOMBRE", "Pedro");
        map.put("CATEGORIA", "CSD");
        Mockito.when(excelUtils.readFile(inputStream)).thenReturn(Collections.singletonList(map));

        //When
        List<Employee> actual = employeeService.loadFromFile(fileName);

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual.get(0).getId(), (Integer) 1);
        Assert.assertEquals(actual.get(0).getName(), "Pedro");
        Assert.assertEquals(actual.get(0).getCategory(), Category.CSD);
    }

    @Test
    public void testDeleteAll() {
        //When
        employeeService.deleteAll();

        //Then
        Mockito.verify(employeeStore).deleteAll();
    }

    @Test
    public void testInsert() {
        //Given
        Employee employee = Mockito.mock(Employee.class);

        //When
        employeeService.insert(employee);

        //Then
        Mockito.verify(employeeStore).insert(employee);
    }

    @Test
    public void testGetAll() {
        //When
        employeeService.getAll();

        //Then
        Mockito.verify(employeeStore).getAll();
    }

    @Test
    public void testGetBy() {
        //When
        employeeService.getBy(5);

        //Then
        Mockito.verify(employeeStore).getEmployeeById(5);
    }

}