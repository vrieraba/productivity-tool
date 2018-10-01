var employeesEfficiencyReports = [];
var teamEfficiencyReports = [];

$("#loadEfficiencyReport").click(function() {
    employeesEfficiencyReports = [];
    teamEfficiencyReports = [];
    cleanCharts();

    let employeeId = $('#efficiency_select_employee').val();

    if (employeeId == 0) {
        window["employees"].forEach(function(employee) {
            let reportFilter = new ReportFilter(null, employee.id);
            getEfficiencyReport("employeesEfficiencyReports", printEfficiencyReport, reportFilter);
        });
    } else {
        let reportFilter = new ReportFilter(null, employeeId);
        getEfficiencyReport("employeesEfficiencyReports", printEfficiencyReport, reportFilter);
    }
    let reportFilter = new ReportFilter(null, null);
    getEfficiencyReport("teamEfficiencyReports", printEfficiencyReport, reportFilter);
});

function printEfficiencyReport() {
    printGlobalEfficiencyReport();
    printMonthlyEfficiencyReport();
}

function printGlobalEfficiencyReport() {
    let dataEntries = [];

    dataEntries.push(new ChartDataEntry("Team", [teamEfficiencyReports[0].efficiency]));

    employeesEfficiencyReports.forEach(function(employeeReport) {
        dataEntries.push(new ChartDataEntry(employeeReport.employee.name, [employeeReport.efficiency]));
    });
    addChart(CHART_TYPE.BAR, "efficiency_charts_area", "global_efficiency", "Global Efficiency", ["Efficiency"], dataEntries, CHART_SIZE.BIG);
}

function printMonthlyEfficiencyReport() {
    let dataEntries = [];

    let monthsWithData = [];
    Object.values(Month).forEach(function(monthNumber) {
        let month = getMonthByValue(monthNumber);
        if (teamEfficiencyReports[0].efficiencyByMonth[month] != null) {
            monthsWithData.push(month);
        }
    });

    let coyotesMonthlyEfficiencyValues = [];
    monthsWithData.forEach(function(month) {
        coyotesMonthlyEfficiencyValues.push(teamEfficiencyReports[0].efficiencyByMonth[month]);
    });
    dataEntries.push(new ChartDataEntry("Team", coyotesMonthlyEfficiencyValues));

    employeesEfficiencyReports.forEach(function(employeeReport) {
        let employeeMonthlyEfficiencyValues = [];
        monthsWithData.forEach(function(month) {
            employeeMonthlyEfficiencyValues.push(employeeReport.efficiencyByMonth[month]);

        });
        dataEntries.push(new ChartDataEntry(employeeReport.employee.name, employeeMonthlyEfficiencyValues));
    });

    addChart(CHART_TYPE.LINE, "efficiency_charts_area", "monthly_efficiency", "Monthly Efficiency", monthsWithData, dataEntries, CHART_SIZE.BIG);
}
