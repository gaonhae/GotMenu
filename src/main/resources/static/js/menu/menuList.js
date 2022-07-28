
// 초기값
let target = "메뉴";
let keyword = "";
let pageNo = 0;

function search()
{

    target = $("#target").val();
    keyword = $("#search_kw").val();
    pageNo = 0;

    $.ajax({
        url : "/menu/",
        data : {"target" : target, "keyword" : keyword, "pageNo" : pageNo},
        method : "GET",
        success : createList,
        error :
            e => alert(e.responseText)
    })

}

function pageChange(number)
{
    pageNo = number;
    $.ajax({
        url : "/menu/",
        data : {"target" : target, "keyword" : keyword, "pageNo" : pageNo},
        method : "GET",
        success : createList,
        error :
            e => alert(e.responseText)
    })
}

function createList(object)
{
    if(object.array.length == 0)
    {
        $("#menuBox").html('<div class="menuList-row"><label style="text-decoration: italic; margin: 50px;">no result</label></div>');
        $("#pageBox").html("");
        return;
    }

    var menuHtml = "";
    for(let i=0; i < object.array.length; i++)
    {
        menuHtml +=
        '<div class="menuList-row">'+
        '	<a href="#" class="menu-title">' + object.array[i].menuComposition +'</a>'+
        '	<span class="menu-tags">'+ object.array[i].tags +'</span>'+
        '	<span class="menu-viewed">' +  object.array[i].views + '회 조회</span>'+
        '	<span class="menu-likes">' + object.array[i].menuRating + '</span>'+
        '</div>';
    }

    var pageHtml = ""
    for(let i = object.startBtn; i < object.endBtn; i++)
    {
        if(object.nowBtn == i)
        pageHtml +=
        '<button class="pageBtn" onclick="pageChange(' + i + ')" style="text-decoration: underline;">' + (i+1) + '</button>';
        else
        pageHtml +=
        '<button class="pageBtn" onclick="pageChange(' + i + ')">' + (i+1) + '</button>';
    }

    $("#menuBox").html(menuHtml);
    $("#pageBox").html(pageHtml);
}
