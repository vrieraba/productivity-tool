package com.vriera.productivity.utils;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class DataBaseUtils {

    @Autowired
    JsonSerializer jsonSerializer;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PetitionService petitionService;
    @Autowired
    TaskService taskService;

    public void cleanDB() throws IOException {
        employeeService.deleteAll();
        petitionService.deleteAll();
        taskService.deleteAll();
    }

    public void loadDB() throws IOException {
        System.out.println("Loading DataBase...");

        uploadEmployeesFile("EMPLEADOS.xlsx");

        for (File file : new File("/mnt/ProductivityToolData/").listFiles()) {
            if (file.getName().contains("PETICION_")) {
                uploadPetitionFile(file.getName());
            }
        }
        System.out.println("Load complete!");
        System.out.println(petitionService.getAll().size() + " petitions loaded!");
        System.out.println(taskService.getAll().size() + " tasks loaded!");
    }

    private void uploadPetitionFile(String filename) throws IOException {
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
