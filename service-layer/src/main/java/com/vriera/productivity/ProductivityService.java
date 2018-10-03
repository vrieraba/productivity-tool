package com.vriera.productivity;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskService;
import com.vriera.productivity.tasks.TaskSubType;
import com.vriera.productivity.tasks.TaskType;
import com.vriera.productivity.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductivityService {

    private static final EnumMap<TaskType, List<TaskSubType>> PRODUCTIVE_TASK_SUB_TYPES_BY_TASK_TYPE;
    static {
        PRODUCTIVE_TASK_SUB_TYPES_BY_TASK_TYPE = new EnumMap<>(TaskType.class);
        PRODUCTIVE_TASK_SUB_TYPES_BY_TASK_TYPE.put(TaskType.EVOLUTIVO, Arrays.asList(TaskSubType.ANALISIS, TaskSubType.CODIFICACION, TaskSubType.CORRECCION, TaskSubType.PRUEBA, TaskSubType.DESPLIEGUE));
        PRODUCTIVE_TASK_SUB_TYPES_BY_TASK_TYPE.put(TaskType.SOPORTE, Arrays.asList(TaskSubType.CONSULTA));
    }

    private final PetitionService petitionService;
    private final TaskService taskService;
    private final MathUtils mathUtils;

    @Autowired
    public ProductivityService(PetitionService petitionService, TaskService taskService, MathUtils mathUtils) {
        this.petitionService = petitionService;
        this.taskService = taskService;
        this.mathUtils = mathUtils;
    }

    public double calculateGlobalProductivity(Employee employee, TaskType taskType) {
        List<Task> tasks = new ArrayList<>();
        for (Petition petition : petitionService.getAll()) {
            for (TaskSubType taskSubType : PRODUCTIVE_TASK_SUB_TYPES_BY_TASK_TYPE.get(taskType)) {
                tasks.addAll(taskService.getBy(employee, petition, taskSubType));
            }
        }
        return mathUtils.calculateProductivity(tasks);
    }

    public Map<Month, Double> calculateProductivityByMonth(Employee employee, TaskType taskType) {
        Map<Month, Double> productivityByMonth = new HashMap<>();
        for (Month month : petitionService.getMonthsWithPetitions()) {
            List<Task> tasks = new ArrayList<>();

            for (Petition petition : petitionService.getBy(month)) {
                for (TaskSubType taskSubType : PRODUCTIVE_TASK_SUB_TYPES_BY_TASK_TYPE.get(taskType)) {
                    tasks.addAll(taskService.getBy(employee, petition, taskSubType));
                }
            }
            productivityByMonth.put(month, mathUtils.calculateProductivity(tasks));
        }
        return productivityByMonth;
    }

    public Map<TaskSubType, Map<Month, Double>> calculateProductivityByMonthAndTaskSubType(Employee employee, TaskType taskType) {
        Map<TaskSubType, Map<Month, Double>> productivityByMonthAndTaskSubType = new HashMap<>();

        for (TaskSubType taskSubType : PRODUCTIVE_TASK_SUB_TYPES_BY_TASK_TYPE.get(taskType)) {
            Map<Month, Double> productivityByMonth = new HashMap<>();
            for (Month month : petitionService.getMonthsWithPetitions()) {
                List<Task> tasks = new ArrayList<>();

                for (Petition petition : petitionService.getBy(month)) {
                    tasks.addAll(taskService.getBy(employee, petition, taskSubType));
                }
                productivityByMonth.put(month, mathUtils.calculateProductivity(tasks));
            }
            productivityByMonthAndTaskSubType.put(taskSubType, productivityByMonth);
        }
        return productivityByMonthAndTaskSubType;
    }
}
