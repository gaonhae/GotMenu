package org.TUK.gotMenu.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.form.UserForm;
import org.TUK.gotMenu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping("/") // 여기로 보내서 회원가입 (생성)
    @ResponseBody
    public String signup(@Valid UserForm user, BindingResult bindingResult)
    {
        // 만일 제약 조건을 만족하지 못하면, 오류 메시지를 출력한다.
        if(bindingResult.hasErrors())
        {
            return bindingResult.getAllErrors().get(0).getDefaultMessage();
        }

        // 저장한다.
        try
        {
            userService.create(user);
        }
        catch (Exception e)
        {
            // ID 중복 외에는 전부 처리했음.
            return "중복 ID입니다.";
        }

        return "success";
    }

}
