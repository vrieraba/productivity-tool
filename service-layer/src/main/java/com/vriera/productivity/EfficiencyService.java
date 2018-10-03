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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EfficiencyService {

    static final List<TaskSubType> EFFECTIVE_TASK_SUB_TYPES = Arrays.asList(TaskSubType.ANALISIS, TaskSubType.CODIFICACION, TaskSubType.CORRECCION, TaskSubType.REVISION, TaskSubType.PRUEBA, TaskSubType.DESPLIEGUE, TaskSubType.CONSULTA);

    private final PetitionService petitionService;
    private final TaskService taskService;
    private final MathUtils mathUtils;

    @Autowired
    public EfficiencyService(PetitionService petitionService, TaskService taskService, MathUtils mathUtils) {
        this.petitionService = petitionService;
        this.taskService = taskService;
        this.mathUtils = mathUtils;
    }

    public double calculateGlobalEfficiency(Employee employee) {
        return mathUtils.calculateEfficiency(taskService.getBy(employee), EFFECTIVE_TASK_SUB_TYPES);
    }


    public Map<Month, Double> calculateEfficiencyByMonth(Employee employee) {
        Map<Month, Double> efficiencyByMonth = new HashMap<>();
        for (Month month : petitionService.getMonthsWithPetitions()) {
            List<Task> tasks = new ArrayList<>();
            for (Petition petition : petitionService.getBy(month)) {
                tasks.addAll(taskService.getBy(employee, petition));
            }
            efficiencyByMonth.put(month, mathUtils.calculateEfficiency(tasks, EFFECTIVE_TASK_SUB_TYPES));
        }
        return efficiencyByMonth;
    }
}
