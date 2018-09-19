package com.vriera.productivity.petitions;

import com.vriera.productivity.Month;

import java.util.List;

public class PetitionFilterBuilder {

    private List<Month> months;

    public PetitionFilterBuilder months(List<Month> months) {
        this.months = months;
        return this;
    }

    public List<Month> getMonths() {
        return months;
    }

    public PetitionFilter build() {
        return new PetitionFilter(this);
    }
}
