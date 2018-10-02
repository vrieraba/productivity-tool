package com.vriera.productivity.employees;

import com.vriera.productivity.Cache;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeStore {

    public void deleteAll() {
        Cache.employees.clear();
    }

    public void insert(Employee employee) {
        Cache.employees.add(employee);
    }

    public List<Employee> getAll() {
        return new ArrayList<Employee>(Cache.employees);
    }

    public Employee getEmployeeById(Integer id) {
        List<Employee> employees = getAll();
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }
}
