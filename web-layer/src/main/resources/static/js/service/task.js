function getTasks(dataTargetDestination, callbackFunction, taskFilter) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/task/",
        data: {
            employeeId : taskFilter.employeeId,
            taskSubType: taskFilter.taskSubType,
            month: taskFilter.month
        },
        dataType: 'json',
        cache: false,
        timeout: 6000,
        beforeSend: function () {
            pushAjaxCall();
        },
        success: function (data) {
            window[dataTargetDestination] = data;
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