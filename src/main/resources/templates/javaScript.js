$(document).ready(function() {
    $("#showPass a").on('click', function(event) {
        event.preventDefault();
        if($('#showPass input').attr("type") == "text"){
            $('#showPass input').attr('type', 'password');
            $('#showPass i').addClass( "fa-eye-slash" );
            $('#showPass i').removeClass( "fa-eye" );
        }else if($('#showPass input').attr("type") == "password"){
            $('#showPass input').attr('type', 'text');
            $('#showPass i').removeClass( "fa-eye-slash" );
            $('#showPass i').addClass( "fa-eye" );
        }
    });
});


