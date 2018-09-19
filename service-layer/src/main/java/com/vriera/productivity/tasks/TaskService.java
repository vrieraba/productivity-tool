package com.vriera.productivity.tasks;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.utils.ExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    private ExcelUtils excelUtils;
    @Autowired
    private TaskStore taskStore;

    public void loadFromFile(Petition petition, String fileName) throws IOException {
        List<Map<String, String>> rows = excelUtils.readFile(Files.newInputStream(Paths.get("/mnt/ProductivityToolData/" + fileName)));
        for (Map<String, String> row : rows) {
            if (matchInsertConditions(row)) {
                TaskBuilder builder = new TaskBuilder();
                builder.petitionId(petition.getId());
                builder.employeeId(Integer.valueOf(row.get("Responsable")));
                builder.taskType(TaskType.findByDisplayText(row.get("Tipo")));
                builder.taskSubType(TaskSubType.valueOf(row.get("Subtipo")));
                builder.estimated(Integer.valueOf(row.get("Esfuerzo")));
                builder.reported(Integer.valueOf(row.get("Incurrido")));
                taskStore.insert(builder.build());
            }
        }
    }

    private boolean matchInsertConditions(Map<String, String> row) {
        return StringUtils.isNotEmpty(row.get("Responsable")) && !"125601".equals(row.get("Responsable")) && !TaskType.CORRECCION_INCIDENCIA.equals(TaskType.findByDisplayText(row.get("Tipo")));
    }

    public void deleteAll() {
        taskStore.deleteAll();
    }

    public List<Task> getAll() {
        return taskStore.getAll();
    }

    public List<Task> getBy(Employee employee) {
        return taskStore.getTaskBy(employee, null, null);
    }

    public List<Task> getBy(Employee employee,  Petition petition) {
        return taskStore.getTaskBy(employee, petition, null);
    }

    public List<Task> getBy(Employee employee, Petition petition, TaskSubType taskSubType) {
        return taskStore.getTaskBy(employee, petition, taskSubType);
    }

    public List<Task> getBy(TaskFilter taskFilter) {
        return taskStore.getTaskBy(taskFilter);
    }
}
