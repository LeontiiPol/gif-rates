$(document).ready(function() {
    $('#rateBtn').click(function () {
        let code = $('#currencies').val();
        $.ajax({
            url: 'http://localhost:8082/api/gif-rates/rates/' + code,
            type: "GET",
            contentType: "application/json",
            success: function (result) {
                $('#rateGif').attr("src", result.gif_url);
                $('#rateGif').attr("width", result.width);
                $('#rateGif').attr("height", result.height);
            }
        });
    })
});