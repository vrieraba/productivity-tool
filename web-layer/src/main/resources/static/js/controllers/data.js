$("#bt_load_data").click(function(event) {
    loadData(updateDataInfo);
});

function updateDataInfo(dataSummary) {
    $("#staticNumEmployees").val(dataSummary.numEmployees);
    $("#staticNumPetitions").val(dataSummary.numPetitions);
    $("#staticNumTasks").val(dataSummary.numTasks);
    initProductivityTool();
}