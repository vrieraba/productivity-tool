package com.vriera.productivity;

import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.TaskService;
import com.vriera.productivity.utils.DataBaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class DataController {

    @Autowired
    private DataBaseUtils dataBaseUtils;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PetitionService petitionService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/loadData")
    public DataSummary loadData() {
        try {
            dataBaseUtils.cleanDB();
            dataBaseUtils.loadDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataSummaryBuilder builder = new DataSummaryBuilder();
        builder.numEmployees(employeeService.getAll().size());
        builder.numPetitions(petitionService.getAll().size());
        builder.numTasks(taskService.getAll().size());
        return builder.build();
    }
}
