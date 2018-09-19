package com.vriera.productivity;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskService;
import com.vriera.productivity.tasks.TaskSubType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EfficiencyService {

    private static final List<TaskSubType> EFFECTIVE_TASK_SUB_TYPES = Arrays.asList(TaskSubType.ANALISIS, TaskSubType.CODIFICACION, TaskSubType.PRUEBA, TaskSubType.DESPLIEGUE, TaskSubType.CONSULTA);

    private final PetitionService petitionService;
    private final TaskService taskService;

    @Autowired
    public EfficiencyService(PetitionService petitionService, TaskService taskService) {
        this.petitionService = petitionService;
        this.taskService = taskService;
    }

    public double calculateGlobalEfficiency(Employee employee) {
        return calculateEfficiency(taskService.getBy(employee));
    }


    public Map<Month, Double> calculateEfficiencyByMonth(Employee employee) {
        Map<Month, Double> efficiencyByMonth = new HashMap<>();
        for (Month month : petitionService.getMonthsWithPetitions()) {
            List<Task> tasks = new ArrayList<>();
            for (Petition petition : petitionService.getBy(month)) {
                tasks.addAll(taskService.getBy(employee, petition));
            }
            efficiencyByMonth.put(month, calculateEfficiency(tasks));
        }
        return efficiencyByMonth;
    }


    private double calculateEfficiency(List<Task> tasks) {
        double effectiveTimeReported = 0;
        for (Task task : tasks) {
            if (EFFECTIVE_TASK_SUB_TYPES.contains(task.getTaskSubType())) {
                effectiveTimeReported += task.getTimeReported();
            }
        }
        double totalTimeReported = 0;
        for (Task task : tasks) {
            totalTimeReported += task.getTimeReported();
        }
        double efficiency = (effectiveTimeReported / totalTimeReported) * 100.0;
        return new BigDecimal(efficiency).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
