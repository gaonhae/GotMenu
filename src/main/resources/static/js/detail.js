
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
               if(message.userNo == null) alert("없음");

               $("#userNo").val(message.userNo);
               $("#id").val(message.id);
               $("#password").val(message.password);
               $("#joinDate").val(message.joinDate);
               $("#userDetail").text(message.userDetail);

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
        $("#delete").hide();
        mode = !mode;
    }
    else
    {

        // 업데이트할 정보 전송
        var id = $("#id").val();
        var password = $("#password").val();
        var userDetail = $("#userDetail").val();
        var data = {"userNo" : getNo(), "id" : id, "password" : password, "userDetail" : userDetail};

        $.ajax({
               url: "/user/",
               type: "PUT",
               data: data,

               success : function(message)
               {

                    if(message == "success")
                    {
                         $("#revise").text("수정");
                         $("#id").attr("disabled", true);
                         $("#password").attr("disabled", true);
                         $("#userDetail").attr("disabled", true);
                         $("#delete").show();
                         mode = !mode;
                    }
                    else
                    {
                        alert(message);
                    }

               },
               error : function(e)
               {
                   alert(e.responseText);
               }
        });

    }
 }

    function remove()
    {
                $.ajax({
                       url: "/user/",
                       type: "DELETE",
                       data: {"userNo" : getNo()},

                       success : function(message)
                       {
                            if(message == "success")
                            {
                                location.href = "/";
                            }
                            else
                            {
                                alert(message);
                            }

                       },
                       error : function(e)
                       {
                           alert(e.responseText);
                       }
                });

    }


