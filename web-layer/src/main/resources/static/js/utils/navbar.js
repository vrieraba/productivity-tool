$("#navbar_home").click(function (event) {
    $(".content_section").hide();
    $("#home_section").show();
    collapseSideBar();
});

$("#navbar_report_dedication").click(function (event) {
    $(".content_section").hide();
    $("#dedication_section").show();
    collapseSideBar();
});

$("#navbar_report_productivity").click(function (event) {
    $(".content_section").hide();
    $("#productivity_section").show();
    collapseSideBar();
});

$("#navbar_report_efficiency").click(function (event) {
    $(".content_section").hide();
    $("#efficiency_section").show();
    collapseSideBar();
});

$("#navbar_report_lead_time").click(function (event) {
    $(".content_section").hide();
    $("#lead_time_section").show();
    collapseSideBar();
});

$("#navbar_data_summary").click(function (event) {
    $(".content_section").hide();
    $("#data_section").show();
    collapseSideBar();
});



function expandSideBar() {
    $('#sidebar').addClass('active');
    $('.overlay').addClass('active');
    $('.collapse.in').toggleClass('in');
    $('a[aria-expanded=true]').attr('aria-expanded', 'false');
}

function collapseSideBar() {
    $('#sidebar').removeClass('active');
    $('.overlay').removeClass('active');
}