package org.TUK.gotMenu.controller;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.entity.Menu;
import org.TUK.gotMenu.form.MenuForm;
import org.TUK.gotMenu.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/menu")
@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page){
        Page<Menu> paging = this.menuService.getList(page);
        model.addAttribute("paging", paging);	//템플릿에 페이징에 대한 내용을 넘겨줌
        return "menuList";
    }

    @RequestMapping("/detail/{menuNo}")
    public String detail(Model model, @PathVariable("menuNo") Integer id) {
        Menu menu = this.menuService.getMenu(id);
        model.addAttribute("menu", menu);	//템플릿에 질문의 상세 내용을 넘겨줌
        return "menuDetail";
    }

    @GetMapping("/create")
    public String create(MenuForm menuForm){
       return "menuForm";
    }

    @PostMapping("/create")
    public String create(@Valid MenuForm menuForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "menuForm";
        }
        this.menuService.create(menuForm.getMenuComposition(), menuForm.getMenuDetail(), menuForm.getMenuDescription(), 0, menuForm.getTags());
        return "redirect:/menu/list";
    }



}
