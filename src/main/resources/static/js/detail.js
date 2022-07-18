
function getNo()
{
    return  document.location.href.split("/")[4];
}


function detail()
{
    var data = {"userNo" : getNo()};

    $.ajax({
           url: "/user/",
           type: "GET",
           data: data,

           success : function(message)
           {
               $("#userNo").val(message.userNo);
               $("#id").val(message.id);
               $("#password").val(message.password);
               $("#joinDate").val(message.joinDate);
               $("#userDetail").val(message.userDetail);

               if(message.same)
               {
                    $("#alter").show();
               }

           },
           error : function(e)
           {
               alert(e.responseText);
           }
    });

}

let mode = false;

function reviseMode()
{
    if(!mode)
    {
        $("#revise").text("완료");
        $("#id").attr("disabled", false);
        $("#password").attr("disabled", false);
        $("#userDetail").attr("disabled", false);
    }
    else
    {
        $("#revise").text("수정");
        $("#id").attr("disabled", true);
        $("#password").attr("disabled", true);
        $("#userDetail").attr("disabled", true);
    }
    mode = !mode;

}