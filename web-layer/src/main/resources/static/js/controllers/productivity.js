var employeesProductivityReports = [];
var teamProductivityReports = [];

$("#loadProductivityReport").click(function() {
    employeesProductivityReports = [];
    teamProductivityReports = [];
    cleanCharts();

    let employeeId = $('#productivity_select_employee').val();
    let serviceType = $('input[name=productivity_service_type]:checked').val();

    if (employeeId == 0) {
        window["employees"].forEach(function(employee) {
            let reportFilter = new ReportFilter(serviceType, employee.id);
            getProductivityReport("employeesProductivityReports", printProductivityReport, reportFilter);
        });
    } else {
        let reportFilter = new ReportFilter(serviceType, employeeId);
        getProductivityReport("employeesProductivityReports", printProductivityReport, reportFilter);
    }
    let reportFilter = new ReportFilter(serviceType, null);
    getProductivityReport("teamProductivityReports", printProductivityReport, reportFilter);
});

function printProductivityReport() {
    printGlobalProductivityReport();
    printMonthlyProductivityReport();
    printTaskSubTypeProductivityReport();
}

function printGlobalProductivityReport() {
    let dataEntries = [];

    dataEntries.push(new ChartDataEntry("Team", [teamProductivityReports[0].productivity]));

    employeesProductivityReports.forEach(function(employeeReport) {
        dataEntries.push(new ChartDataEntry(employeeReport.employee.name, [employeeReport.productivity]));
    });
    addChart(CHART_TYPE.BAR, "productivity_charts_area", "global_productivity", "Global Productivity", ["Productivity"], dataEntries, CHART_SIZE.BIG);
}

function printMonthlyProductivityReport() {
    let dataEntries = [];

    let monthsWithData = [];
    Object.values(Month).forEach(function(monthNumber) {
        let month = getMonthByValue(monthNumber);
        if (teamProductivityReports[0].productivityByMonth[month] != null) {
            monthsWithData.push(month);
        }
    });

    let coyotesMonthlyProductivityValues = [];
    monthsWithData.forEach(function(month) {
        coyotesMonthlyProductivityValues.push(teamProductivityReports[0].productivityByMonth[month]);
    });
    dataEntries.push(new ChartDataEntry("Team", coyotesMonthlyProductivityValues));

    employeesProductivityReports.forEach(function(employeeReport) {
        let employeeMonthlyProductivityValues = [];
        monthsWithData.forEach(function(month) {
            employeeMonthlyProductivityValues.push(employeeReport.productivityByMonth[month]);

        });
        dataEntries.push(new ChartDataEntry(employeeReport.employee.name, employeeMonthlyProductivityValues));
    });

    addChart(CHART_TYPE.LINE, "productivity_charts_area", "monthly_productivity", "Monthly Productivity", monthsWithData, dataEntries, CHART_SIZE.BIG);
}

function printTaskSubTypeProductivityReport() {
    Object.keys(teamProductivityReports[0].productivityByMonthAndTaskSubType).forEach(function(taskSubType) {
        let dataEntries = [];

        let monthsWithData = [];
        Object.values(Month).forEach(function(monthNumber) {
            let month = getMonthByValue(monthNumber);
            if (teamProductivityReports[0].productivityByMonth[month] != null) {
                monthsWithData.push(month);
            }
        });

        let coyotesMonthlyProductivityValues = [];
        monthsWithData.forEach(function(month) {
            coyotesMonthlyProductivityValues.push(teamProductivityReports[0].productivityByMonthAndTaskSubType[taskSubType][month]);
        });
        dataEntries.push(new ChartDataEntry("Team", coyotesMonthlyProductivityValues));

        employeesProductivityReports.forEach(function(employeeReport) {
            let employeeMonthlyProductivityValues = [];
            monthsWithData.forEach(function(month) {
                employeeMonthlyProductivityValues.push(employeeReport.productivityByMonthAndTaskSubType[taskSubType][month]);

            });
            dataEntries.push(new ChartDataEntry(employeeReport.employee.name, employeeMonthlyProductivityValues));
        });
        addChart(CHART_TYPE.LINE, "productivity_charts_area", taskSubType + "productivity", taskSubType + " Productivity", monthsWithData, dataEntries, CHART_SIZE.BIG);
    });
}