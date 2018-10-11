var employees = [];
var months = [];

var onGoingAjaxCalls = 0;

function pushAjaxCall() {
    showLoader();
    onGoingAjaxCalls++;
}

function popAjaxCall() {
    onGoingAjaxCalls--;
    if (onGoingAjaxCalls == 0) {
        hideLoader();
    }
}

function initProductivityTool() {
    getEmployees("employees", addEmployees);
    months = Object.keys(Month);
}

function addEmployees() {
    $("select[id$='select_employee']").append($('<option>', {value:-1, text:'(*) Team Average'}));
    $("select[id$='select_employee']").append($('<option>', {value:-2, text:'(*) Full Team'}));
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