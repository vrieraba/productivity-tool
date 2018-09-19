package com.vriera.productivity.employees;

public class EmployeeBuilder {

    private Integer id;
    private String name;
    private Category category;

    public EmployeeBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public EmployeeBuilder name(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder category(Category category) {
        this.category = category;
        return this;
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

    public Employee build() {
        return new Employee(this);
    }

}
