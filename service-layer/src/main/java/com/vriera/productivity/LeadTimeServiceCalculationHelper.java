package com.vriera.productivity;

import com.google.common.math.Quantiles;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.petitions.PetitionService;
import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskService;
import com.vriera.productivity.tasks.TaskSubType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeadTimeServiceCalculationHelper {

    private final PetitionService petitionService;
    private final TaskService taskService;

    @Autowired
    public LeadTimeServiceCalculationHelper(PetitionService petitionService, TaskService taskService) {
        this.petitionService = petitionService;
        this.taskService = taskService;
    }

    List<Task> getLeadTimeTasks(Employee employee, Month month, TaskSubType taskSubType) {
        List<Task> tasks = new ArrayList<>();
        for(Petition petition : petitionService.getBy(month, 3)) {
            tasks.addAll(taskService.getBy(employee, petition, taskSubType));
        }
        return tasks;
    }

    double calculatePercentile(List<Task> tasks, int percentile) {
        List<Integer> timesReported = new ArrayList<>();
        for (Task task : tasks) {
            timesReported.add(task.getTimeReported());
        }
        if (timesReported.size() > 0) {
            return Quantiles.percentiles().index(percentile).compute(timesReported);
        } else {
            return 0.00;
        }
    }

}
