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
                    getCommentList(0);
                }
                else
                {
                    alert(message);
                }
            },
            error : (e) => alert(e.responseText)

        });
}

function getCommentList(pageNo)
{
    $.ajax({
        url     : "/comment/",
        data    : {"menuNo" : getNo(), "pageNo" : pageNo},
        method  : "GET",
        success : getCommentSuccess,
        fail    : e => alert(e.responseText)

    })
}


function getCommentSuccess(data)
{
    // 댓글 리스트를 뿌려주는 부분
    var commentHtml = "";
    for(var i=0; i < data.array.length; i++)
    {
        var userDetail = "/user/" + data.array[i].writerNo;
        var update = "update(" + i + ", " + data.array[i].commentNo + ")";
        var remove = "remove(" + data.array[i].commentNo + ")";

        commentHtml +=  '<div class="commentBox">'+
        '			<div class="commentTitle">'+
        '				<a class="writerId" href="' + userDetail + '"> ' + data.array[i].writerId + '</a>'+
        '				<label>' + data.array[i].resistDate + '</label>'+
        '			</div>'+
        '			<div>'+
        '				<textarea class="commentContent" cols=50 rows=3 disabled>' + data.array[i].content + '</textarea>'+
        '			</div>';

        if(data.array[i].isSameUser) // 만약 현재 유저와 작성자가 같다면 수정/삭제 버튼을 보여준다.
            commentHtml +=
        '			<div class="buttonBox">'+
        '				<button onclick="' + update + '">수정</button> <button onclick="' + remove + '">삭제</button>'+
        '			</div>'+
        '		</div>';
        else // 아니라면 그냥 div를 닫아준다.
            commentHtml += '		</div>';

    }

    // 페이지 버튼을 만들어주는 부분
    var pageHtml = "";
    for(var i = data.btnStart; i < data.btnEnd; i++)
    {
        var link = "getCommentList(" + i + ")";
        if(i == data.pageNo) pageHtml += '<button style="text-decoration: underline;" onclick="' + link + '">' + (i+1) + '</button>';
        else pageHtml += '<button onclick="' + link + '">' + (i+1) + '</button>';
    }

    $("#commentContainer").html(commentHtml);
    $("#pageBox").html(pageHtml);

}

var mode = [false, false, false, false, false];
function update(index, commentNo)
{
    if(mode[index])
    {


    }
    else
    {


    }

}

function remove(commentNo)
{
    var result = confirm("삭제하시겠습니까?");
    if(result == false) return;

    $.ajax({
        url : '/comment/',
        data : {"commentNo" : commentNo},
        method : "DELETE",
        success : message => {
            if(message == "success")
             {
                getCommentList(0);
                alert("댓글 삭제 성공");
             }
            else alert(message);
        },
        error : e => alert(e.responseText)
    })

}

