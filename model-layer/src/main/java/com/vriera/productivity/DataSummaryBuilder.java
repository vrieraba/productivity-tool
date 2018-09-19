package com.vriera.productivity;

public class DataSummaryBuilder {

    private Integer numEmployees;
    private Integer numPetitions;
    private Integer numTasks;

    public DataSummaryBuilder numEmployees(Integer numEmployees) {
        this.numEmployees = numEmployees;
        return this;
    }

    public DataSummaryBuilder numPetitions(Integer numPetitions) {
        this.numPetitions = numPetitions;
        return this;
    }

    public DataSummaryBuilder numTasks(Integer numTasks) {
        this.numTasks = numTasks;
        return this;
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

    public DataSummary build() {
        return new DataSummary(this);
    }
}
