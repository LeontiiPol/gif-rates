$(document).ready(function() {
    $.ajax({
        url: 'http://localhost:8082/api/gif-rates/currencies/codes',
        type: "GET",
        contentType: "application/json",
        success: function (result) {
            $.each(result.codes, function (index, value) {
                if (index === 0) {
                    $('#currencies').append('<option selected="selected" value="' + value + '">' + value + '</option>');
                } else {
                    $('#currencies').append('<option value="' + value + '">' + value + '</option>');
                }
            });
        }
    });
})
