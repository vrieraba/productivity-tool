var employeesDedicationReports = [];
var teamDedicationReports = [];

$("#loadDedicationReport").click(function() {
    employeesDedicationReports = [];
    teamDedicationReports = [];
    cleanCharts();

    let employeeId = $('#dedication_select_employee').val();

    if (employeeId == 0) {
        window["employees"].forEach(function(employee) {
            let reportFilter = new ReportFilter(null, employee.id);
            getDedicationReport("employeesDedicationReports", printDedicationReport, reportFilter);
        });
    } else {
        let reportFilter = new ReportFilter(null, employeeId);
        getDedicationReport("employeesDedicationReports", printDedicationReport, reportFilter);
    }
    let reportFilter = new ReportFilter(null, null);
    getDedicationReport("teamDedicationReports", printDedicationReport, reportFilter);
});

function printDedicationReport() {
    printGlobalDedicationReport();
    printDedicationReportByMonthAndSubtask();
}

function printGlobalDedicationReport() {
    let taskSubTypes = Object.keys(teamDedicationReports[0].dedicationByTaskSubType);
    let dataEntries = [];

    let teamDedicationBySubTask = [];
    taskSubTypes.forEach(function(taskSubType) {
        teamDedicationBySubTask.push(teamDedicationReports[0].dedicationByTaskSubType[taskSubType]);
    });
    dataEntries.push(new ChartDataEntry("Coyotes", teamDedicationBySubTask));

    employeesDedicationReports.forEach(function(employeeReport) {
        let employeeDedicationBySubTask = [];
        taskSubTypes.forEach(function(taskSubType) {
            employeeDedicationBySubTask.push(employeeReport.dedicationByTaskSubType[taskSubType]);
        });
        dataEntries.push(new ChartDataEntry(employeeReport.employee.name, employeeDedicationBySubTask));
    });

    addChart(CHART_TYPE.BAR, "dedication_charts_area", "global_dedication", "Global Dedication", taskSubTypes, dataEntries, CHART_SIZE.BIG);

}

function printDedicationReportByMonthAndSubtask() {
    Object.keys(teamDedicationReports[0].dedicationByMonthAndTaskSubType).forEach(function(taskSubType) {
        let dataEntries = [];

        let monthsWithData = [];
        Object.values(Month).forEach(function(monthNumber) {
            let month = getMonthByValue(monthNumber);
            if (teamDedicationReports[0].dedicationByMonthAndTaskSubType[taskSubType][month] != null) {
                monthsWithData.push(month);
            }
        });

        let coyotesMonthlyDedicationValues = [];
        monthsWithData.forEach(function(month) {
            coyotesMonthlyDedicationValues.push(teamDedicationReports[0].dedicationByMonthAndTaskSubType[taskSubType][month]);
        });
        dataEntries.push(new ChartDataEntry("Coyotes", coyotesMonthlyDedicationValues));

        employeesDedicationReports.forEach(function(employeeReport) {
            let employeeMonthlyDedicationValues = [];
            monthsWithData.forEach(function(month) {
                employeeMonthlyDedicationValues.push(employeeReport.dedicationByMonthAndTaskSubType[taskSubType][month]);

            });
            dataEntries.push(new ChartDataEntry(employeeReport.employee.name, employeeMonthlyDedicationValues));
        });
        addChart(CHART_TYPE.LINE, "dedication_charts_area", taskSubType + "_dedication", taskSubType + " Dedication", monthsWithData, dataEntries, CHART_SIZE.BIG);
    });
}