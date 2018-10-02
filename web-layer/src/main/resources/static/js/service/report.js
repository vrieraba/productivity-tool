function getProductivityReport(dataTargetDestination, callbackFunction, reportFilter) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/productivity-report/",
        data: {
            employeeId : reportFilter.employeeId,
            serviceType : reportFilter.serviceType
        },
        dataType: 'json',
        cache: false,
        timeout: 6000,
        beforeSend: function () {
            pushAjaxCall();
        },
        success: function (data) {
            window[dataTargetDestination].push(data);
        },
        error: function (result, status, err) {
            alert(result.responseText + " - " + status.responseText + " - " + err.Message);
        },
        complete: function() {
            popAjaxCall();
            if (window["onGoingAjaxCalls"] == 0) {
                callbackFunction();
            }
        }
    });
}

function getDedicationReport(dataTargetDestination, callbackFunction, reportFilter) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/dedication-report/",
        data: {
            employeeId : reportFilter.employeeId
        },
        dataType: 'json',
        cache: false,
        timeout: 6000,
        beforeSend: function () {
            pushAjaxCall();
        },
        success: function (data) {
            window[dataTargetDestination].push(data);
        },
        error: function (result, status, err) {
            alert(result.responseText + " - " + status.responseText + " - " + err.Message);
        },
        complete: function() {
            popAjaxCall();
            if (window["onGoingAjaxCalls"] == 0) {
                callbackFunction();
            }
        }
    });
}

function getEfficiencyReport(dataTargetDestination, callbackFunction, reportFilter) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/efficiency-report/",
        data: {
            employeeId : reportFilter.employeeId
        },
        dataType: 'json',
        cache: false,
        timeout: 6000,
        beforeSend: function () {
            pushAjaxCall();
        },
        success: function (data) {
            window[dataTargetDestination].push(data);
        },
        error: function (result, status, err) {
            alert(result.responseText + " - " + status.responseText + " - " + err.Message);
        },
        complete: function() {
            popAjaxCall();
            if (window["onGoingAjaxCalls"] == 0) {
                callbackFunction();
            }
        }
    });
}

function getLeadTimeReport(dataTargetDestination, callbackFunction, reportFilter) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/lead-time-report/",
        data: {
            employeeId : reportFilter.employeeId
        },
        dataType: 'json',
        cache: false,
        timeout: 6000,
        beforeSend: function () {
            pushAjaxCall();
        },
        success: function (data) {
            window[dataTargetDestination].push(data);
        },
        error: function (result, status, err) {
            alert(result.responseText + " - " + status.responseText + " - " + err.Message);
        },
        complete: function() {
            popAjaxCall();
            if (window["onGoingAjaxCalls"] == 0) {
                callbackFunction();
            }
        }
    });
}