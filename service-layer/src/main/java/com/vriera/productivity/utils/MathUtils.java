package com.vriera.productivity.utils;

import com.vriera.productivity.tasks.Task;
import com.vriera.productivity.tasks.TaskSubType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class MathUtils {

    public Double round(Double value, int decimals) {
        return new BigDecimal(value).setScale(decimals, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double calculateProductivity(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return 0.00;
        } else {
            double timeReported = 0.00;
            for (Task task : tasks) {
                timeReported += task.getTimeReported();
            }
            double productivity = tasks.size() / timeReported;
            return round(productivity, 2);
        }
    }

    public double calculateEfficiency(List<Task> tasks, List<TaskSubType> effectiveTaskSubType) {
        double effectiveTimeReported = 0.00;
        for (Task task : tasks) {
            if (effectiveTaskSubType.contains(task.getTaskSubType())) {
                effectiveTimeReported += task.getTimeReported();
            }
        }
        double totalTimeReported = 0.00;
        for (Task task : tasks) {
            totalTimeReported += task.getTimeReported();
        }
        double efficiency = 0.00;
        if (totalTimeReported > 0.0) {
            efficiency = round(((effectiveTimeReported / totalTimeReported) * 100.0), 2);
        }
        return efficiency;
    }

    public double calculateDedication(List<Task> tasks) {
        double dedication = 0.00;
        for (Task task : tasks) {
            dedication += task.getTimeReported();
        }
        return dedication;
    }

    public int calculateDifferentEmployees(List<Task> tasks) {
        List<Integer> employeeIds = new ArrayList<>();
        for (Task task : tasks) {
            if (!employeeIds.contains(task.getEmployeeId())) {
                employeeIds.add(task.getEmployeeId());
            }
        }
        return employeeIds.size();
    }

}
