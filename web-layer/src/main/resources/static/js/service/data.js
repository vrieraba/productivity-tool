function loadData(callback) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/load-data",
        dataType: 'json',
        cache: false,
        timeout: 50000,
        beforeSend: function() {
            pushAjaxCall();
        },
        success: function (data) {
            callback(data);
        },
        error: function (result, status, err) {
            alert(result.responseText + " - " + status.responseText + " - " + err.Message);
        },
        complete: function () {
            popAjaxCall();
        }
    });
}

function getMonths(dataDestinationTarget, callbackFunction) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/months",
        dataType: 'json',
        cache: false,
        timeout: 50000,
        beforeSend: function() {
            pushAjaxCall();
        },
        success: function (data) {
            window[dataDestinationTarget] = data;
            if (callbackFunction != null) {
                callbackFunction();
            }
        },
        error: function (result, status, err) {
            alert(result.responseText + " - " + status.responseText + " - " + err.Message);
        },
        complete: function () {
            popAjaxCall();
        }
    });
}

function getTaskSubTypes(dataDestinationTarget, callbackFunction) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/task-sub-types",
        dataType: 'json',
        cache: false,
        timeout: 50000,
        beforeSend: function() {
            pushAjaxCall();
        },
        success: function (data) {
            window[dataDestinationTarget] = data;
            if (callbackFunction != null) {
                callbackFunction();
            }
        },
        error: function (result, status, err) {
            alert(result.responseText + " - " + status.responseText + " - " + err.Message);
        },
        complete: function () {
            popAjaxCall();
        }
    });
}