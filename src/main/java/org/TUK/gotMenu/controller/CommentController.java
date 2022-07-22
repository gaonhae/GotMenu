package org.TUK.gotMenu.controller;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.form.CommentForm;
import org.TUK.gotMenu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public void read(HttpServletResponse response, @Param("menuNo") int menuNo )
    {
        try
        {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json"); // JSON
            response.getWriter().println(commentService.read(menuNo));
        }
        catch(Exception e){e.printStackTrace();}

    }




}
