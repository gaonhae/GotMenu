package org.TUK.gotMenu.service;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.DataNotFoundException;
import org.TUK.gotMenu.entity.Menu;
import org.TUK.gotMenu.entity.User;
import org.TUK.gotMenu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    @Autowired
    private final CommentService commentService;
    @Autowired
    private final SecurityService securityService;

    public void create(String menuComposition, String menuDetail, String menuDescription, int menuRating, String tags) {
        Menu m = new Menu();
        m.setMenuComposition(menuComposition);
        m.setMenuDetail(menuDetail);
        m.setMenuDescription(menuDescription);
        m.setMenuRating(menuRating);
        m.setTags(tags);

        User writer = new User();
        writer.setUserNo(securityService.getUserNo());
        m.setWriter(writer);

        this.menuRepository.save(m);
    }

    public Page<Menu> getList(int page, String kw) {    //MenuList 템플릿에 넘겨 페이징을 위해 사용
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("menuNo"));
        Pageable pageable = PageRequest.of(page, 30, Sort.by(sorts));
        return this.menuRepository.findAllByKeyword(kw, pageable);
    }

    public Menu getMenu(Integer menuNo) {
        Optional<Menu> menu = this.menuRepository.findById(menuNo);
        if (menu.isPresent()) {
            return menu.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void modify(Menu menu, String menuComposition, String mennuDetail, String menuDescription, String tags) {
        menu.setMenuComposition(menuComposition);
        menu.setMenuDetail(mennuDetail);
        menu.setMenuDescription(menuDescription);
        menu.setTags(tags);
        this.menuRepository.save(menu);
    }

    public void delete(Menu menu) {
        // 내부에 있는 코멘트들을 직접 삭제해줘야 User와의 연관이 해결되어 삭제할 수 있다.
        menu.getCommentList().stream().forEach(comment -> commentService.delete(comment.getCommentNo()));
        this.menuRepository.delete(menu);
    }

    public String[] splitTags(Menu menu) {
        String joinedTags = menu.getTags();
        String[] splitedTags = joinedTags.split("#");
        return splitedTags;
    }


    public List<Menu> filter(String flt) {
        List<Menu> menus = this.menuRepository.findAll();
        List<Menu> tmp = new ArrayList<Menu>();
        for (Menu m : menus) {
            for (String s : splitTags(m)) {
                if (s == flt) {
                    tmp.add(m);
                    break;
                }
            }
        }
        return tmp;
    }



}
