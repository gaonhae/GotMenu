<<<<<<< HEAD

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
        success : message => alert(message),
        error :
            e => alert(e.responseText)
    })

}

function createList(message)
{


}



=======
>>>>>>> 7c1afcf3b5b2e1c03a52be5ff83a584b89882e68
