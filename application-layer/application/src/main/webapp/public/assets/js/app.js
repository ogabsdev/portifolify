const defineNavbarHeight = () => $('#navbar').height($(document).height());

const handleFormUpdateProject = () => {
    $("#submit-update-project").click(() => {
        $("#update-project").submit();
    });
};

const handleFormDeleteProject = () => {
    $("#submit-delete-project").click(() => {
        $("#delete-project").submit();
    });
};

$(document).ready(() => {
    defineNavbarHeight();
    handleFormUpdateProject();
    handleFormDeleteProject();
});