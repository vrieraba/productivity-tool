package com.vriera.productivity;

public class DataSummary {

    private final Integer numEmployees;
    private final Integer numPetitions;
    private final Integer numTasks;

    DataSummary(DataSummaryBuilder builder) {
        numEmployees = builder.getNumEmployees();
        numPetitions = builder.getNumPetitions();
        numTasks = builder.getNumTasks();
    }

    public Integer getNumEmployees() {
        return numEmployees;
    }

    public Integer getNumPetitions() {
        return numPetitions;
    }

    public Integer getNumTasks() {
        return numTasks;
    }
}
