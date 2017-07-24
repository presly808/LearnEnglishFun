/**
 * Created by diversaint on 07.07.17.
 */
$(document).ready(function(){
    $('.modal').modal();

    $('#submitRegister').click(register);

    toMain();

})
function toMain() {
    $('#loginContainer').show();
    $('#registerPart').show();
}

function register() {
    $.ajax({
        type: 'POST',
        url: "/register",
        data: JSON.stringify({
            email: $('#email').val(),
            pass: $('#password').val()
        }),
        success: function (result) {
            if (result == 'User successfully created') {
                $('#modalText').html("Successfully registration! Sign in, please.");
                $('#modal2').modal('open');
            } else {
                $('#modalText').html(result);
                $('#modal2').modal('open');
            }
        }

    });
}

