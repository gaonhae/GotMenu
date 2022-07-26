function signup()
{
    // 폼 객체화
    var form = $("#signup")[0];
    var data = new FormData(form);

    if(document.querySelector("#pw").value == document.querySelector("#repw").value){
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
    else{
        alert("비밀번호를 확인해주세요")
    }


}
