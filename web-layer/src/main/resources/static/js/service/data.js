function loadData(callback) {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/loadData",
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