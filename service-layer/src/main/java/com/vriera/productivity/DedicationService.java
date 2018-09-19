package com.vriera.productivity;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskService;
import com.vriera.productivity.tasks.TaskSubType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DedicationService {

    private final PetitionService petitionService;
    private final TaskService taskService;
    private final EmployeeService employeeService;

    @Autowired
    public DedicationService(PetitionService petitionService, TaskService taskService, EmployeeService employeeService) {
        this.petitionService = petitionService;
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    public Map<TaskSubType, Double> calculateDedicationByTaskSubType(Employee employee) {
        Map<TaskSubType, Double> dedicationByTaskSubType = new HashMap<>();

        List<Petition> petitions = petitionService.getAll();
        for (TaskSubType taskSubType : TaskSubType.values()) {
            double dedication = 0.0;
            for (Petition petition : petitions) {
                for (Task task : taskService.getBy(employee, petition, taskSubType)) {
                    dedication += task.getTimeReported();
                }
            }
            if (employee == null) {
                dedication = dedication / employeeService.getAll().size();
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
                List<Petition> petitions = petitionService.getBy(month);

                double dedication = 0.0;
                for (Petition petition : petitions) {
                    for (Task task : taskService.getBy(employee, petition, taskSubType)) {
                        dedication += task.getTimeReported();
                    }
                }
                if (employee == null) {
                    dedication = dedication / employeeService.getAll().size();
                }
                dedicationByMonth.put(month, dedication);
            }
            dedicationByMonthAndTaskSubType.put(taskSubType, dedicationByMonth);
        }

        return dedicationByMonthAndTaskSubType;
    }

}


