function cleanFilters(section_id) {
    $("#" + section_id).empty();
}

function addMonthsFilter(section_id, filter_id, isNoSelection) {
    let sub_wrapper = $('<div class="col-md-10"></div>');
    let wrapper = $('<div class="form-group row"><label for="' + filter_id +'" class="col-md-2 col-form-label">Month</label></div>');
    let select = $('<select class="form-control" id="' + filter_id + '"></select>');

    if (isNoSelection) {
        select.append($('<option>', {value: null, text:'-'}));
    }
    $.each(window["months"], function(i, item) {
        select.append($('<option>', {value: item, text: item}));
    });
    sub_wrapper.append(select);
    wrapper.append(sub_wrapper);
    $("#" + section_id).append(wrapper);
}

function addServiceTypeFilter(section_id, filter_id, isNoSelection) {
    let sub_wrapper = $('<div class="col-md-10"></div>');
    let wrapper = $('<div class="form-group row"><label for="' + filter_id +'" class="col-md-2 col-form-label">Month</label></div>');
    let select = $('<select class="form-control" id="' + filter_id + '"></select>');

    if (isNoSelection) {
        select.append($('<option>', {value: null, text:'-'}));
    }
    select.append($('<option>', {value: "EVOLUTIVO", text: "Evolutivo"}));
    select.append($('<option>', {value: "CORRECTIVO", text: "Correctivo"}));
    select.append($('<option>', {value: "SOPORTE", text: "Soporte"}));

    sub_wrapper.append(select);
    wrapper.append(sub_wrapper);
    $("#" + section_id).append(wrapper);
}

function addEmployeesFilter(section_id, filter_id, isNoSelection, isTeamAverage, isFullTeam) {
    let sub_wrapper = $('<div class="col-md-10"></div>');
    let wrapper = $('<div class="form-group row"><label for="' + filter_id +'" class="col-md-2 col-form-label">Employee</label></div>');
    let select = $('<select class="form-control" id="' + filter_id + '"></select>');

    if (isNoSelection) {
        select.append($('<option>', {value: null, text:'-'}));
    }
    if (isTeamAverage) {
        select.append($('<option>', {value:-1, text:'(*) Team Average'}));
    }
    if (isFullTeam) {
        select.append($('<option>', {value:-2, text:'(*) Full Team'}));
    }
    $.each(window["employees"], function(i, item) {
        select.append($('<option>', {value:item.id, text:item.name}));
    });
    sub_wrapper.append(select);
    wrapper.append(sub_wrapper);
    $("#" + section_id).append(wrapper);
}

function addButton(section_id, button_id, button_text) {
    let wrapper = $('<div class="form-group row"  style="text-align: center;"><div class="col"></div></div>');
    let button = $('<button id="' + button_id + '" class="btn btn-primary">' + button_text + '</button>');
    wrapper.append(button);
    $("#" + section_id).append(wrapper);
}

function getSelectValue(section_id, filter_id) {
    let value = $('#' + section_id).find('#' + filter_id).val();
    if (value === "-") {
        value = null;
    }
    return value;
}