package com.vriera.productivity.controllers;

import com.vriera.productivity.DataSummary;
import com.vriera.productivity.DataSummaryBuilder;
import com.vriera.productivity.Month;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.TaskService;
import com.vriera.productivity.tasks.TaskSubType;
import com.vriera.productivity.utils.DataBaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class DataController {

    private final DataBaseUtils dataBaseUtils;
    private final EmployeeService employeeService;
    private final PetitionService petitionService;
    private final TaskService taskService;

    @Autowired
    public DataController(DataBaseUtils dataBaseUtils, EmployeeService employeeService, PetitionService petitionService, TaskService taskService) {
        this.dataBaseUtils = dataBaseUtils;
        this.employeeService = employeeService;
        this.petitionService = petitionService;
        this.taskService = taskService;
    }

    @GetMapping("/load-data")
    public DataSummary loadData() {
        dataBaseUtils.cleanDB();
        try {
            dataBaseUtils.loadDB();
        } catch (IOException | ParseException e) {
            System.out.println("ERROR:" + e.getMessage());
        }
        DataSummaryBuilder builder = new DataSummaryBuilder();
        builder.numEmployees(employeeService.getAll().size());
        builder.numPetitions(petitionService.getAll().size());
        builder.numTasks(taskService.getAll().size());
        return builder.build();
    }

    @GetMapping("/months")
    public List<Month> getMonths() {
        return petitionService.getMonthsWithPetitions();
    }

    @GetMapping("/task-sub-types")
    public List<TaskSubType> getTaskSubTypes() {
        return Arrays.asList(TaskSubType.values());
    }
}
