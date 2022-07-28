
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

function createList(object)
{
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

    $("#menuBox").html(menuHtml);

}
