package org.TUK.gotMenu.controller;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.form.CommentForm;
import org.TUK.gotMenu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/comment")
public class CommentController
{
    @Autowired
    final CommentService commentService;

    @PostMapping("/")
    @ResponseBody
    public String create(@Valid CommentForm comment, BindingResult errors)
    {
        if(errors.hasErrors())
            return errors.getAllErrors().get(0).getDefaultMessage();

        // 성공 시, "success"
        // 실패 시, "DB 오류"
        return commentService.create(comment);
    }
    @GetMapping("/")
    @ResponseBody
    public void read(HttpServletResponse response, @Param("menuNo") int menuNo, @Param("pageNo") int pageNo)
    {
        // 메뉴 번호랑 페이지 번호 넣어주면 해당 댓글들의 정보와 페이지 버튼을 JSON으로 반환해준다.
        try
        {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(commentService.read(menuNo, pageNo));
        }
        catch(Exception e){e.printStackTrace();}

    }

    @PutMapping("/")
    @ResponseBody
    public String update(@Param("comment") int commentNo, @Param("content") String content)
    {
        if(content.length() < 1 || 500 < content.length()) return "내용은 1자 이상 500자 미만이어야 합니다.";

        // 성공 시 success 반환, 실패시 "수정에 실패했습니다." 반환.
        return commentService.update(commentNo, content);
    }

    @DeleteMapping("/")
    @ResponseBody
    public String delete(@Param("commentNo") int commentNo)
    {
        // 성공 시 "success", 실패 시 "댓글 삭제에 실패했습니다."
        return commentService.delete(commentNo);
    }

}
