var employees = [];
var onGoingAjaxCalls = 0;

function pushAjaxCall() {
    $("#loader_background").show();
    $("#loader").show();
    onGoingAjaxCalls++;
}

function popAjaxCall() {
    onGoingAjaxCalls--;
    if (onGoingAjaxCalls == 0) {
        $("#loader_background").hide();
        $("#loader").hide();
    }
}

function initProductivityTool() {
    getEmployees("employees", addEmployeesToSelect);
}

function addEmployeesToSelect() {
    $("select[id$='select_employee']").find("option").remove().end().append($('<option>', {value:0, text:'-'}));
    $.each(employees, function(i, item) {
        $("select[id$='select_employee']").append($('<option>', {value:item.id, text:item.name}));
    });
}

$(document).ready(function () {
    $("#sidebar").mCustomScrollbar({
        theme: "minimal"
    });

    $('#dismiss, .overlay').on('click', function () {
        collapseSideBar();
    });

    $('#sidebarCollapse').on('click', function () {
        expandSideBar();
    });

    $("#home_section").show();
});