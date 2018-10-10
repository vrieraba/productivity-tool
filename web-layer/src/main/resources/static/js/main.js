var employees = [];
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
}

function addEmployees() {
    $("select[id$='select_employee']").append($('<option>', {value:-1, text:'(*) Team Average'}));
    $("select[id$='select_employee']").append($('<option>', {value:-2, text:'(*) Full Team'}));
    $.each(employees, function(i, item) {
        $("select[id$='select_employee']").append($('<option>', {value:item.id, text:item.name}));
    });

}

function addEmployeesToSelect(input_id, isNoSelection, isTeamAverage, isFullTeam) {
    let selectInput = $("select[id=" + input_id + "]");
    selectInput.children('option').remove();
    if (isNoSelection) {
        selectInput.append($('<option>', {value:null, text:'-'}));
    }
    if (isTeamAverage) {
        selectInput.append($('<option>', {value:-1, text:'(*) Team Average'}));
    }
    if (isFullTeam) {
        selectInput.append($('<option>', {value:-2, text:'(*) Full Team'}));
    }
    $.each(employees, function(i, item) {
        selectInput.append($('<option>', {value:item.id, text:item.name}));
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