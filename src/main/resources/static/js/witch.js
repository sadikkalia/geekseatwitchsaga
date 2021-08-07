$(document).ready(function () {
    idToUse = 3;
    $("#expelwitch").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });

    $("#btn-add-person").click(function () {
        var person = '<div class="form-group form-group-lg">\n' +
            '                <label class="">Person '+String.fromCharCode(97 + idToUse-1).toUpperCase()+':</label>\n' +
            '                <label class="col-sm-2 control-label">Age of Death:</label>\n' +
            '                <div class="col-sm-2">\n' +
            '                    <input type="text" class="form-control" id="ageOfDeath'+idToUse+'"/>\n' +
            '                </div>\n' +
            '                <label class="col-sm-2 control-label">Year of Death:</label>\n' +
            '                <div class="col-sm-2">\n' +
            '                    <input type="text" class="form-control" id="yearOfDeath'+idToUse+'"/>\n' +
            '                </div>\n' +
            '            </div>';
        $("#peopleContainer").append(person);


        idToUse++;
    });

});

var idToUse = 3;

function fire_ajax_submit() {

    var array = [];
    for (var i = 1; i < idToUse; i++) {
        var jsonObject = { ageOfDeath: $("#ageOfDeath"+i).val(), yearOfDeath: $("#yearOfDeath"+i).val()};
        array.push(jsonObject);
    }

    $("#btn-submit").prop("disabled", true);
    $("#btn-add-person").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/witchsaga/expelwitch",
        data: JSON.stringify(array),
        dataType: 'json',
        cache: false,
        timeout: 10000,
        success: function (data) {
            if (data == -1) {
                $('#feedback').html("People Input Not Correct!</br>");
            }
            else {
                $('#feedback').html("The average is: " + data + " !</br></br> <b><i>The Coder Has Returned!!! Now We Are Free!</b></i></br>");
            }

            $("#btn-submit").prop("disabled", false);
            $("#btn-add-person").prop("disabled", false);

        },
        error: function (e) {
            $('#feedback').html(e.responseText);

            console.log("ERROR : ", e);
            $("#btn-submit").prop("disabled", false);
            $("#btn-add-person").prop("disabled", false);

        }
    });

}