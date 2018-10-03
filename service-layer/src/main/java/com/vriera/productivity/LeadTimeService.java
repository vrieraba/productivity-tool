package com.vriera.productivity;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskSubType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeadTimeService {

    private static List<TaskSubType> LEAD_TIME_TASK_SUB_TYPES = Arrays.asList(TaskSubType.ANALISIS, TaskSubType.CODIFICACION, TaskSubType.CORRECCION, TaskSubType.PRUEBA, TaskSubType.DESPLIEGUE);
    private static Integer PERCENTILE = 85;

    private final LeadTimeServiceCalculationHelper calculationHelper;
    private final PetitionService petitionService;

    @Autowired
    public LeadTimeService(LeadTimeServiceCalculationHelper calculationHelper, PetitionService petitionService) {
        this.calculationHelper = calculationHelper;
        this.petitionService = petitionService;
    }

    public Map<Month, Double> calculateLeadTimeByMonth(Employee employee) {
        HashMap<Month, Double> leadTimeByMonth = new HashMap<>();
        for (Month month : petitionService.getMonthsWithPetitions()) {
            double leadTime = 0;
            for (TaskSubType taskSubType : LEAD_TIME_TASK_SUB_TYPES) {
                List<Task> tasks = calculationHelper.getLeadTimeTasks(employee, month, taskSubType);
                leadTime += calculationHelper.calculatePercentile(tasks, PERCENTILE);
            }
            leadTimeByMonth.put(month, leadTime);
        }
        return leadTimeByMonth;
    }

    public Map<TaskSubType ,Map<Month, Double>> calculateLeadTimeByMonthAndTaskSubType(Employee employee) {
        Map<TaskSubType ,Map<Month, Double>> leadTimeByMonthAndTaskSubType = new HashMap<>();
        for (TaskSubType taskSubType : LEAD_TIME_TASK_SUB_TYPES) {
            HashMap<Month, Double> leadTimeByMonth = new HashMap<>();
            for (Month month : petitionService.getMonthsWithPetitions()) {
                List<Task> tasks = calculationHelper.getLeadTimeTasks(employee, month, taskSubType);
                double leadTime = calculationHelper.calculatePercentile(tasks, PERCENTILE);
                leadTimeByMonth.put(month, leadTime);
            }
            leadTimeByMonthAndTaskSubType.put(taskSubType, leadTimeByMonth);
        }
        return leadTimeByMonthAndTaskSubType;
    }


}
