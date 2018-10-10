package com.vriera.productivity.controllers.tasks;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class TaskControllerTest {

    @Mock
    private TaskService taskService;
    @Mock
    private EmployeeService employeeService;

    private TaskController taskController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        taskController = new TaskController(taskService, employeeService);
    }

    @Test
    public void testGetTasks() {
        //Given
        Integer employeeId = 12345;

        Employee employee = Mockito.mock(Employee.class);
        Mockito.when(employeeService.getBy(employeeId)).thenReturn(employee);

        Task task = Mockito.mock(Task.class);
        Mockito.when(taskService.getBy(employee)).thenReturn(Collections.singletonList(task));

        //When
        List<Task> actual = taskController.getTasks(employeeId);

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual, Collections.singletonList(task));
    }

    @Test
    public void testGetTasksWithoutEmployee() {
        //Given
        Integer employeeId = null;

        Task task = Mockito.mock(Task.class);
        Mockito.when(taskService.getBy(null)).thenReturn(Collections.singletonList(task));

        //When
        List<Task> actual = taskController.getTasks(employeeId);

        //Then
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual, Collections.singletonList(task));
        Mockito.verifyZeroInteractions(employeeService);
    }

}