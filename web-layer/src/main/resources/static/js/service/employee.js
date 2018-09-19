function getEmployees(dataDestinationTarget, callbackFunction) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/employee/",
        dataType: 'json',
        cache: false,
        timeout: 50000,
        success: function (data) {
            window[dataDestinationTarget] = data;
            if (callbackFunction != null) {
                callbackFunction();
            }
        },
        error: function (result, status, err) {
            alert(result.responseText + " - " + status.responseText + " - " + err.Message);
        }
    });
}