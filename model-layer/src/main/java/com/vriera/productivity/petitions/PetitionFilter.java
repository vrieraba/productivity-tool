package com.vriera.productivity.petitions;

import com.vriera.productivity.Month;

import java.util.List;

public class PetitionFilter {

    private final List<Month> months;

    public PetitionFilter(PetitionFilterBuilder builder) {
        months = builder.getMonths();
    }

    public List<Month> getMonths() {
        return months;
    }
}
