package com.vriera.productivity.petitions;

import com.vriera.productivity.Cache;
import com.vriera.productivity.Month;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PetitionStore {

    public void deleteAll() {
        Cache.petitions.clear();
    }

    public void insert(Petition petition) {
        Cache.petitions.add(petition);
    }

    public List<Petition> getAll() {
        return new ArrayList<Petition>(Cache.petitions);
    }

    public List<Petition> getBy(Month month) {
        List<Petition> petitions = new ArrayList<Petition>();
        for (Petition petition : getAll()) {
            if (petition.getMonth().equals(month)) {
                petitions.add(petition);
            }
        }
        return petitions;
    }

    public List<Month> getMonthsWithPetitions() {
        List<Month> months = new ArrayList<>();
        for (Petition petition : getAll()) {
            if (!months.contains(petition.getMonth())) {
                months.add(petition.getMonth());
            }
        }
        return months;
    }


    public List<Petition> getBy(PetitionFilter petitionFilter) {
        List<Petition> allPetitions = getAll();
        List<Petition> targetPetitions = new ArrayList<Petition>();
        for (Petition petition : allPetitions) {
            boolean matchFilters = true;
            if (petitionFilter.getMonths() != null) {
                boolean isPetitionInMonths = false;
                for (Month month : petitionFilter.getMonths()) {
                    if (petition.getMonth().equals(month)) {
                        isPetitionInMonths = true;
                    }
                }
                if(!isPetitionInMonths) {
                    matchFilters = false;
                }
            }
            if (matchFilters) {
                targetPetitions.add(petition);
            }
        }
        return targetPetitions;
    }

}
