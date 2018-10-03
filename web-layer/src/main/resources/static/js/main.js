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
    $.each(employees, function(i, item) {
        $("select[id$='select_employee']").append($('<option>', {value:item.id, text:item.name}));
    });
    $("select[id$='select_employee']").append($('<option>', {value:-1, text:'Team Average'}));
    $("select[id$='select_employee']").append($('<option>', {value:-2, text:'Full Team'}));
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