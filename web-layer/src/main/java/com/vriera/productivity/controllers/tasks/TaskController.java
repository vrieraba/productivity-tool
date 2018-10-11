package com.vriera.productivity.controllers.tasks;

import com.vriera.productivity.Month;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final EmployeeService employeeService;

    @Autowired
    public TaskController(TaskService taskService, EmployeeService employeeService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<Task> getTasks(Integer employeeId, Month month) {
        Employee employee = null;
        if (employeeId != null) {
            employee = employeeService.getBy(employeeId);
        }
        return taskService.getBy(employee, month);
    }
}
