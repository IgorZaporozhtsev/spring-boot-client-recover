$(document).ready(function() {
    $.ajax({url:"http://localhost:8080/account/19", success: function (result) {
            $('#nickname').append(result.nickname);
        }});
});

