var employees = [];
var months = [];
var taskSubTypes = [];

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
    getEmployees("employees", null);
    getMonths("months", null);
    getTaskSubTypes("taskSubTypes", null);
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