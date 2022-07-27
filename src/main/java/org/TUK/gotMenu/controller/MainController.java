package org.TUK.gotMenu.controller;

import lombok.RequiredArgsConstructor;


import org.TUK.gotMenu.service.MenuService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequiredArgsConstructor
@EnableScheduling
@Component
public class MainController {

    private final MenuService menuService;
    String todayMenu = "";

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/random",produces = "application/text; charset=UTF-8")
    public void random(HttpServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(todayMenu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 5000)  //5초에 한 번씩 추천 메뉴를 바꿈
    public void chooseMenu() {
        todayMenu = menuService.randomMenu();
    }


}
