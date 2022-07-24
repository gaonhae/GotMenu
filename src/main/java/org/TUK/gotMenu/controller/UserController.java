package org.TUK.gotMenu.controller;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.form.UserForm;
import org.TUK.gotMenu.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
        return "user/signup";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "user/login";
    }

    @RequestMapping("/{userNo}")
    public String detail()
    {
        return "user/detail";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(UserForm user)
    {
        /*
            성공 시 "success" 반환.
            실패 시 실패 원인 문자열 반환
         */

        return userService.login(user);
    }

    @RequestMapping("/logout")
    public String logout()
    {
        userService.logout();
        return "/main/home";
    }

    @PostMapping("/") // 여기로 보내서 회원가입 (생성)
    @ResponseBody
    public String signup(@Valid UserForm user, BindingResult bindingResult)
    {
        // 만일 제약 조건을 만족하지 못하면, 오류 메시지를 출력한다.
        if (bindingResult.hasErrors())
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

    @GetMapping("/") // 조회 (Read)
    @ResponseBody
    public void getUser(HttpServletResponse response, @Param("userNo") int userNo)
    {
        try
        {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json"); // JSON
            response.getWriter().println(userService.getUserDetail(userNo));
        }
        catch(Exception e){e.printStackTrace();}

    }

    @PutMapping("/") // 수정 (Update)
    @ResponseBody
    public String update(@Valid UserForm user, BindingResult errors)
    {
        if(errors.hasErrors())
        {
            return errors.getAllErrors().get(0).getDefaultMessage();
        }

        return userService.update(user);
    }
    @DeleteMapping("/")
    @ResponseBody
    public String delete(@Param("userNo") int userNo)
    {
        userService.logout();
        userService.delete(userNo);

        return "success";
    }

}
