package com.vriera.productivity.employees;

import com.vriera.productivity.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private ExcelUtils excelUtils;
    @Autowired
    private EmployeeStore employeeStore;

    public List<Employee> loadFromFile(String fileName) throws IOException {
        List<Map<String, String>> rows = excelUtils.readFile(Files.newInputStream(Paths.get("/mnt/ProductivityToolData/" + fileName)));
        List<Employee> employees = new ArrayList<>();
        for (Map<String, String> row : rows) {
            EmployeeBuilder builder = new EmployeeBuilder();
            builder.id(Integer.valueOf(row.get("NUMERO")));
            builder.name(row.get("NOMBRE"));
            builder.category(Category.valueOf(row.get("CATEGORIA")));
            employees.add(builder.build());
        }
        return employees;
    }

    public void deleteAll() {
        employeeStore.deleteAll();
    }

    public void insert(Employee employee) {
        employeeStore.insert(employee);
    }

    public List<Employee> getAll() {
        return employeeStore.getEmployees();
    }

    public Employee getBy(Integer id) {
        return employeeStore.getEmployeeById(id);
    }

}
