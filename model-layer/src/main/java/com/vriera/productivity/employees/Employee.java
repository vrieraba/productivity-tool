package com.vriera.productivity.employees;

public class Employee {

    private final Integer id;
    private final String name;
    private final Category category;

    Employee(EmployeeBuilder builder) {
        id = builder.getId();
        name = builder.getName();
        category = builder.getCategory();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
