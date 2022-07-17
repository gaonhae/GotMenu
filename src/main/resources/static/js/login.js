
function login()
{
    var id = $("#id").val();
    var pw = $("#pw").val();

    var data = {"id" : id, "password" : pw};

    $.ajax({
           url: "/user/login",
           type: "POST",
           data: data,

           success : function(message)
           {
               alert(message);
           },
           error : function(e)
           {
               alert(e.responseText);
           }
    });

}