package org.TUK.gotMenu.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.form.UserForm;
import org.TUK.gotMenu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;

    @RequestMapping("/signup")
    public String signup()
    {
        return "/user/signup";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "user/login";
    }

    @RequestMapping("/detail")
    public String detail()
    {
        return "user/detail";
    }
/*
    @PostMapping("/signup") // 여기로 보내서 회원가입 (생성)
    public String signup(UserForm user, BindingResult error)
    {
        return "success";
    }
 */
}
