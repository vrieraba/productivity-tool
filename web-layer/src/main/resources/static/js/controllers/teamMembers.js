function renderTeamMembersView() {
    showLoader();
    createTeamMembersTableStructure();
    addTeamMembers(window["employees"]);
    hideLoader();
}

function createTeamMembersTableStructure() {
    $('#team_members_table_section').empty().append('<table id="team_members_table" class="table table-bordered"><thead><th>Id</th><th>Name</th><th>Category</th></thead><tbody></tbody></table>');
}

function addTeamMembers(teamMembers) {
    $('#team_members_table').dynatable({
        dataset: {
            records: teamMembers
        }
    });
}