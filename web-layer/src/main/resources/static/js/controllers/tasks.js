var tasks = [];

function renderTasksView() {
    renderTasksFilters();
    createTasksTableStructure();
    addTasks();
}

function renderTasksFilters() {
    cleanFilters("tasks_section_filters");
    addEmployeesFilter("tasks_section_filters", "employee_filter", true, false, false);
    addMonthsFilter("tasks_section_filters", "month_filter", true);
}

function createTasksTableStructure() {
    $('#tasks_section_table').empty().append('<table id="tasks_table" class="table table-bordered"><thead><th data-dynatable-column="employeeId">Employee Id</th><th data-dynatable-column="name">Name</th><th data-dynatable-column="taskType">Type</th><th data-dynatable-column="taskSubType">SubType</th><th data-dynatable-column="timeEstimated">Estimated</th><th data-dynatable-column="timeReported">Reported</th></thead><tbody></tbody></table>');
}

function addTasks() {
    $('#tasks_table').dynatable({
        dataset: {
            records: tasks
        }
    });
}

$("#bt_load_tasks").click(function() {
    tasks = [];
    createTasksTableStructure();
    let taskFilter = new TaskFilter(getSelectValue("employee_filter"), getSelectValue("month_filter"));
    getTasks("tasks", addTasks, taskFilter);
});


