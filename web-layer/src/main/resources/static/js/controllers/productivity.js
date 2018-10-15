var employeesProductivityReports = [];
var teamProductivityReports = [];

function renderProductivityView() {
    renderProductivityFilters();
}

function renderProductivityFilters() {
    cleanFilters("productivity_section_filters");
    addEmployeesFilter("productivity_section_filters", "productivity_select_employee", false, true, true);
    addServiceTypeFilter("productivity_section_filters", "productivity_service_type", false);
    addButton("productivity_section_filters", "loadProductivityReport", "Generate Report")
}

$("#productivity_section_filters").on("click", "#loadProductivityReport", function() {
    employeesProductivityReports = [];
    teamProductivityReports = [];
    cleanCharts();

    let employeeId = getSelectValue("productivity_section_filters", "productivity_select_employee");
    let serviceType = getSelectValue("productivity_section_filters", "productivity_service_type");

    if (employeeId == -1) {
        //Do not show employees
    } else if (employeeId == -2) {
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