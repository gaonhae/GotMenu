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
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value = "kw", defaultValue = "") String kw){
        Page<Menu> paging = this.menuService.getList(page, kw);
        model.addAttribute("paging", paging);	//템플릿에 페이징에 대한 내용을 넘겨줌
        model.addAttribute("kw", kw);   //템플릿에 검색 키워드에 대한 내용을 넘겨줌
        return "menuList";
    }

    @RequestMapping("/{menuNo}/detail")
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

    @GetMapping("/{menuNo}/modify")
    public String modify(MenuForm menuForm, @PathVariable("menuNo") Integer menuNo){
        Menu menu = this.menuService.getMenu(menuNo);
        menuForm.setMenuDescription(menu.getMenuDescription());
        menuForm.setMenuDetail(menu.getMenuDetail());
        menuForm.setMenuComposition(menu.getMenuComposition());
        menuForm.setTags(menu.getTags());
        return "menuForm";
    }


    @PostMapping("/{menuNo}/modify")
    public String modify(@Valid MenuForm menuForm, @PathVariable("menuNo") Integer menuNo, BindingResult bindingResult){
        Menu menu = this.menuService.getMenu(menuNo);
        if(bindingResult.hasErrors()){
            return "menuForm";
        }
        this.menuService.modify(menu, menuForm.getMenuComposition(), menuForm.getMenuDetail(), menuForm.getMenuDescription(), menuForm.getTags());
        return String.format("redirect:/menu/%s/detail",menuNo);
    }


    @GetMapping("/{menuNo}/delete")
    public String delete(@PathVariable("menuNo") Integer menuNo){
        Menu menu = this.menuService.getMenu(menuNo);
        this.menuService.delete(menu);
        return "redirect:/menu/list";
    }


    @GetMapping("/list/filter")
    public void filter(){

    }

}
