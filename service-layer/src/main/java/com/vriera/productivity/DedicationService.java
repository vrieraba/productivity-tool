package com.vriera.productivity;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskService;
import com.vriera.productivity.tasks.TaskSubType;
import com.vriera.productivity.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DedicationService {

    private final PetitionService petitionService;
    private final TaskService taskService;
    private final MathUtils mathUtils;

    @Autowired
    public DedicationService(PetitionService petitionService, TaskService taskService, MathUtils mathUtils) {
        this.petitionService = petitionService;
        this.taskService = taskService;
        this.mathUtils = mathUtils;
    }

    public Map<TaskSubType, Double> calculateDedicationByTaskSubType(Employee employee) {
        Map<TaskSubType, Double> dedicationByTaskSubType = new HashMap<>();

        for (TaskSubType taskSubType : TaskSubType.values()) {
            List<Task> tasks = new ArrayList<>();
            for (Petition petition : petitionService.getAll()) {
                tasks.addAll(taskService.getBy(employee, petition, taskSubType));
            }
            double dedication = mathUtils.calculateDedication(tasks);
            if (employee == null && !tasks.isEmpty()) {
                dedication = mathUtils.round(dedication / mathUtils.calculateDifferentEmployees(tasks), 2);
            }
            dedicationByTaskSubType.put(taskSubType, dedication);
        }

        return dedicationByTaskSubType;
    }

    public Map<TaskSubType, Map<Month, Double>> calculateDedicationByMonthAndTaskSubType(Employee employee) {
        Map<TaskSubType, Map<Month, Double>> dedicationByMonthAndTaskSubType = new HashMap<>();

        for (TaskSubType taskSubType : TaskSubType.values()) {
            Map<Month, Double> dedicationByMonth = new HashMap<>();

            for (Month month : petitionService.getMonthsWithPetitions()) {
                List<Task> tasks = new ArrayList<>();
                for (Petition petition : petitionService.getBy(month)) {
                    tasks.addAll(taskService.getBy(employee, petition, taskSubType));
                }
                double dedication = mathUtils.calculateDedication(tasks);
                if (employee == null && !tasks.isEmpty()) {
                    dedication = mathUtils.round(dedication / mathUtils.calculateDifferentEmployees(tasks), 2);
                }
                dedicationByMonth.put(month, dedication);
            }
            dedicationByMonthAndTaskSubType.put(taskSubType, dedicationByMonth);
        }

        return dedicationByMonthAndTaskSubType;
    }

}


