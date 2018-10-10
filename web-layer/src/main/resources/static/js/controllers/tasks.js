var tasks = [];

function renderTasksView() {
    createTasksTableStructure();
    addTasks();
    addEmployeesToSelect("tasks_select_employee", true, false, false);
}

function createTasksTableStructure() {
    $('#tasks_table_section').empty().append('<table id="tasks_table" class="table table-bordered"><thead><th data-dynatable-column="employeeId">Employee Id</th><th data-dynatable-column="name">Name</th><th data-dynatable-column="taskType">Type</th><th data-dynatable-column="taskSubType">SubType</th><th data-dynatable-column="timeEstimated">Estimated</th><th data-dynatable-column="timeReported">Reported</th></thead><tbody></tbody></table>');
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
    let taskFilter = new TaskFilter(null);
    getTasks("tasks", addTasks, taskFilter);
});


