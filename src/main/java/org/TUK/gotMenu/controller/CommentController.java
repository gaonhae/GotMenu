package org.TUK.gotMenu.controller;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.form.CommentForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class CommentController
{
    @PostMapping("/")
    @ResponseBody
    public String create(@Valid CommentForm comment, BindingResult errors)
    {
        if(errors.hasErrors())
            return errors.getAllErrors().get(0).getDefaultMessage();

        return "success";
    }

}
