package com.vriera.productivity.tasks;

import com.vriera.productivity.employees.Employee;
import com.vriera.productivity.employees.EmployeeService;
import com.vriera.productivity.petitions.Petition;
import com.vriera.productivity.utils.ExcelUtils;
import com.vriera.productivity.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    private final TaskStore taskStore;
    private final ExcelUtils excelUtils;
    private final FileUtils fileUtils;
    private final EmployeeService employeeService;

    @Autowired
    public TaskService(TaskStore taskStore, ExcelUtils excelUtils, FileUtils fileUtils, EmployeeService employeeService) {
        this.taskStore = taskStore;
        this.excelUtils = excelUtils;
        this.fileUtils = fileUtils;
        this.employeeService = employeeService;
    }

    public List<Task> loadFromFile(Petition petition, String fileName) throws IOException {
        List<Task> tasks = new ArrayList<>();

        List<Map<String, String>> rows = excelUtils.readFile(fileUtils.getInputSteam(fileName));
        for (Map<String, String> row : rows) {
            if (matchInsertConditions(row)) {
                TaskBuilder builder = new TaskBuilder();
                builder.petitionId(petition.getId());
                builder.employeeId(Integer.valueOf(row.get("Responsable")));
                builder.taskType(TaskType.findByDisplayText(row.get("Tipo")));
                builder.taskSubType(TaskSubType.valueOf(row.get("Subtipo")));
                builder.estimated(Double.valueOf(row.get("Esfuerzo")));
                builder.reported(Double.valueOf(row.get("Incurrido")));
                Task task = builder.build();
                tasks.add(task);
                taskStore.insert(task);
            }
        }
        return tasks;
    }

    private boolean matchInsertConditions (Map<String, String> row) {
        return StringUtils.isNotEmpty(row.get("Responsable")) && !TaskType.CORRECCION_INCIDENCIA.equals(TaskType.findByDisplayText(row.get("Tipo"))) && employeeService.getBy(Integer.valueOf(row.get("Responsable"))) != null;
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
}
