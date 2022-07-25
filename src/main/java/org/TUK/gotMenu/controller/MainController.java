package org.TUK.gotMenu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController
{
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }
}
