var userNo;
var userId;

function getSession(no, id)
{
    userNo = no;
    userId = id;
}

function getNo()
{
    return  document.location.href.split("/")[4];
}

function create()
{
        var content = $("#myContent").val();
        $.ajax({
            url: "/comment/",
            data : {"userNo" : userNo, "userId" : userId, "content" : content, "menuNo" : getNo()},
            method : "POST",
            success :
            function(message)
            {
                if(message == "success")
                {
                    $("#myContent").val("");
                    getCommentList();
                }
                else
                {
                    alert(message);
                }
            },
            error : (e) => alert(e.responseText)

        });
}

function getCommentList()
{
    $.ajax({
        url     : "/comment/",
        data    : {"menuNo" : getNo()},
        method  : "GET",
        success : setCommentList,
        fail    : e => alert(e.responseText)

    })
}


function setCommentList(data)
{
    var html = "";
    for(var i=0; i < data.length; i++)
    {
        var userDetail = "/user/" + data.array[i].writerNo;
        html +=  '<div class="commentBox">'+
        '			<div class="commentTitle">'+
        '				<a class="writerId" href="' + userDetail + '"> ' + data.array[i].writerId + '</a>'+
        '				<label>' + data.array[i].resistDate + '</label>'+
        '			</div>'+
        '			<div>'+
        '				<textarea class="commentContent" cols=50 rows=5 disabled>' + data.array[i].content + '</textarea>'+
        '			</div>'+
        '			<div class="buttonBox">'+
        '				<button onclick="">수정</button> <button onclick="">삭제</button>'+
        '			</div>'+
        '		</div>';

    }
    $("#commentContainer").html(html);

}

function update()
{

}

function remove()
{

}

