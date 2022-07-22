

function signup()
{
    // 폼 객체화
    var form = $("#signup")[0];
    var data = new FormData(form);

     $.ajax({
            url: "/user/",
            type: "POST",
            data: data,
            contentType: false,
            processData: false,

            success : function(message)
            {
                    if(message == "success") location.href = "/";
                    else alert(message);
            },
            error : function(e)
            {
                alert(e.responseText);
            }
     });

}
