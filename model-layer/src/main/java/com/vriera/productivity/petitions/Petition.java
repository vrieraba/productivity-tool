package com.vriera.productivity.petitions;

import com.vriera.productivity.Month;

public class Petition {

    private final Integer id;
    private final Month month;
    private final PetitionType petitionType;

    Petition(PetitionBuilder builder) {
        id = builder.getId();
        month = builder.getMonth();
        petitionType = builder.getPetitionType();
    }

    public Integer getId() {
        return id;
    }

    public Month getMonth() {
        return month;
    }

    public PetitionType getPetitionType() {
        return petitionType;
    }
}
