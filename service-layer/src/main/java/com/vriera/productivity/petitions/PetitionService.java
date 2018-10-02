package com.vriera.productivity.petitions;

import com.vriera.productivity.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetitionService {

    private final PetitionStore petitionStore;

    @Autowired
    public PetitionService(PetitionStore petitionStore) {
        this.petitionStore = petitionStore;
    }

    public Petition loadFromFile(String fileName) {
        String[] petitionInfo = fileName.split("\\.")[0].split("_");
        PetitionBuilder builder = new PetitionBuilder();
        builder.id(Integer.valueOf(petitionInfo[1]));
        builder.petitionType(PetitionType.valueOf(petitionInfo[2]));
        builder.month(Month.valueOf(petitionInfo[3]));

        Petition petition = builder.build();
        return petition;
    }

    public void deleteAll() {
        petitionStore.deleteAll();
    }

    public void insert(Petition petition) {
        petitionStore.insert(petition);
    }

    public List<Petition> getAll() {
        return petitionStore.getAll();
    }

    public List<Petition> getBy(Month month) {
        return petitionStore.getBy(month);
    }

    public List<Petition> getBy(Month currentMonth, int timeWindow) {
        Month month = currentMonth;
        List<Petition> petitions = new ArrayList<>(petitionStore.getBy(month));
        for(int i = 1; i <timeWindow; i++) {
            month = month.getPreviousMonth();
            petitions.addAll(petitionStore.getBy(month));
        }
        return petitions;
    }

    public List<Month> getMonthsWithPetitions() {
        return petitionStore.getMonthsWithPetitions();
    }

}
