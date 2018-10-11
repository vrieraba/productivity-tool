package com.vriera.productivity.utils;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Component
public class DataBaseUtils {

    private final FileUtils fileUtils;
    private final EmployeeService employeeService;
    private final PetitionService petitionService;
    private final TaskService taskService;

    @Autowired
    public DataBaseUtils(FileUtils fileUtils, EmployeeService employeeService, PetitionService petitionService, TaskService taskService) {
        this.fileUtils = fileUtils;
        this.employeeService = employeeService;
        this.petitionService = petitionService;
        this.taskService = taskService;
    }

    public void cleanDB() {
        employeeService.deleteAll();
        petitionService.deleteAll();
        taskService.deleteAll();
    }

    public void loadDB() throws IOException, ParseException {
        System.out.println("INFO: Loading DataBase...");

        uploadEmployeesFile("EMPLEADOS.xlsx");

        for (File file : fileUtils.getFiles()) {
            if (file.getName().contains("PETICION_")) {
                uploadPetitionFile(file.getName());
            }
        }
        System.out.println("INFO: Load complete!");
        System.out.println("INFO: " + petitionService.getAll().size() + " petitions loaded!");
        System.out.println("INFO: " + taskService.getAll().size() + " tasks loaded!");
    }

    private void uploadPetitionFile(String filename) throws IOException, ParseException {
        Petition petition = petitionService.loadFromFile(filename);
        petitionService.insert(petition);
        taskService.loadFromFile(petition, filename);
    }

    private void uploadEmployeesFile(String filename) throws IOException {
        List<Employee> employees = employeeService.loadFromFile(filename);
        for (Employee employee : employees) {
            employeeService.insert(employee);
        }
    }
}
