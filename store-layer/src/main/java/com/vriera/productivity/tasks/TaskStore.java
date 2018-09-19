package com.vriera.productivity.tasks;

import com.vriera.productivity.Cache;
import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.Petition;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskStore {

    public void deleteAll() {
        Cache.tasks.clear();
    }

    public void insert(Task task) {
        Cache.tasks.add(task);
    }

    public List<Task> getAll() {
        return new ArrayList<Task>(Cache.tasks);
    }

    public List<Task> getTaskBy(Employee employee, Petition petition, TaskSubType taskSubType) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : getAll()) {
            if ((employee == null || task.getEmployeeId().equals(employee.getId()))
                    && (petition == null || task.getPetitionId().equals(petition.getId()))
                    && (taskSubType == null || task.getTaskSubType().equals(taskSubType))) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public List<Task> getTaskBy(TaskFilter taskFilter) {
        List<Task> allTasks = getAll();
        List<Task> targetTasks = new ArrayList<Task>();
        for (Task task : allTasks) {
            boolean matchFilters = true;
            if (taskFilter.getEmployee() != null && !task.getEmployeeId().equals(taskFilter.getEmployee().getId())) {
                matchFilters = false;
            }
            if (matchFilters && taskFilter.getTaskSubTypes() != null) {
                boolean isTaskInSubTypes = false;
                for (TaskSubType taskSubType : taskFilter.getTaskSubTypes()) {
                    if (task.getTaskSubType().equals(taskSubType)) {
                        isTaskInSubTypes = true;
                    }
                }
                if(!isTaskInSubTypes) {
                    matchFilters = false;
                }
            }
            if (matchFilters && taskFilter.getPetitions() != null) {
                boolean isTaskInPetitions = false;
                for (Petition petition : taskFilter.getPetitions()) {
                    if (task.getPetitionId().equals(petition.getId())) {
                        isTaskInPetitions = true;
                    }
                }
                if(!isTaskInPetitions) {
                    matchFilters = false;
                }
            }
            if (matchFilters) {
                targetTasks.add(task);
            }
        }
        return targetTasks;
    }

}
