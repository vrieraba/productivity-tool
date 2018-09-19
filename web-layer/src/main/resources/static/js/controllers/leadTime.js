var employeesLeadTimeReports = [];
var teamLeadTimeReports = [];

$("#loadLeadTimeReport").click(function() {
    employeesLeadTimeReports = [];
    teamLeadTimeReports = [];
    cleanCharts();

    let employeeId = $('#lead_time_select_employee').val();

    if (employeeId == 0) {
        window["employees"].forEach(function(employee) {
            let reportFilter = new ReportFilter(null, employee.id);
            getLeadTimeReport("employeesLeadTimeReports", printLeadTimeReport, reportFilter);
        });
    } else {
        let reportFilter = new ReportFilter(null, employeeId);
        getLeadTimeReport("employeesLeadTimeReports", printLeadTimeReport, reportFilter);
    }
    let reportFilter = new ReportFilter(null, null);
    getLeadTimeReport("teamLeadTimeReports", printLeadTimeReport, reportFilter);
});

function printLeadTimeReport() {
    printMonthlyLeadTimeReport();
    printLeadTimeReportByMonthAndTaskSubType();
}

function printMonthlyLeadTimeReport() {
    let dataEntries = [];

    let monthsWithData = [];
    Object.values(Month).forEach(function(monthNumber) {
        let month = getMonthByValue(monthNumber);
        if (teamLeadTimeReports[0].leadTimeByMonth[month] != null) {
            monthsWithData.push(month);
        }
    });

    let coyotesMonthlyLeadTimesValues = [];
    monthsWithData.forEach(function(month) {
        coyotesMonthlyLeadTimesValues.push(teamLeadTimeReports[0].leadTimeByMonth[month]);
    });
    dataEntries.push(new ChartDataEntry("Coyotes", coyotesMonthlyLeadTimesValues));

    employeesLeadTimeReports.forEach(function(employeeReport) {
        let employeeMonthlyLeadTimeValues = [];
        monthsWithData.forEach(function(month) {
            employeeMonthlyLeadTimeValues.push(employeeReport.leadTimeByMonth[month]);

        });
        dataEntries.push(new ChartDataEntry(employeeReport.employee.name, employeeMonthlyLeadTimeValues));
    });

    addChart(CHART_TYPE.LINE, "lead_time_charts_area", "monthly_lead_time", "Monthly Lead Time", monthsWithData, dataEntries, CHART_SIZE.BIG);
}

function printLeadTimeReportByMonthAndTaskSubType() {
    Object.keys(teamLeadTimeReports[0].leadTimeByMonthAndTaskSubType).forEach(function(taskSubType) {
        let dataEntries = [];

        let monthsWithData = [];
        Object.values(Month).forEach(function(monthNumber) {
            let month = getMonthByValue(monthNumber);
            if (teamLeadTimeReports[0].leadTimeByMonth[month] != null) {
                monthsWithData.push(month);
            }
        });

        let coyotesMonthlyLeadTimeValues = [];
        monthsWithData.forEach(function(month) {
            coyotesMonthlyLeadTimeValues.push(teamLeadTimeReports[0].leadTimeByMonthAndTaskSubType[taskSubType][month]);
        });
        dataEntries.push(new ChartDataEntry("Coyotes", coyotesMonthlyLeadTimeValues));

        employeesLeadTimeReports.forEach(function(employeeReport) {
            let employeeMonthlyLeadTimesValues = [];
            monthsWithData.forEach(function(month) {
                employeeMonthlyLeadTimesValues.push(employeeReport.leadTimeByMonthAndTaskSubType[taskSubType][month]);

            });
            dataEntries.push(new ChartDataEntry(employeeReport.employee.name, employeeMonthlyLeadTimesValues));
        });
        addChart(CHART_TYPE.LINE, "lead_time_charts_area", taskSubType + "_lead_time", taskSubType + " Lead Time", monthsWithData, dataEntries, CHART_SIZE.BIG);
    });
}