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
}
