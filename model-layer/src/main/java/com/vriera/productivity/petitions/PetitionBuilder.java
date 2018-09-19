package com.vriera.productivity.petitions;

import com.vriera.productivity.Month;

public class PetitionBuilder {

    private Integer id;
    private Month month;
    private PetitionType petitionType;

    public PetitionBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public PetitionBuilder month(Month month) {
        this.month = month;
        return this;
    }

    public PetitionBuilder petitionType(PetitionType petitionType) {
        this.petitionType = petitionType;
        return this;
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

    public Petition build() {
        return new Petition(this);
    }
}
