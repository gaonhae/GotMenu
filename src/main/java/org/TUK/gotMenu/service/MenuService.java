package org.TUK.gotMenu.service;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.DataNotFoundException;
import org.TUK.gotMenu.entity.Menu;
import org.TUK.gotMenu.repository.MenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public void create(String menuComposition, String menuDetail, String menuDescription, int menuRating, String tags){
        Menu m = new Menu();
        m.setMenuComposition(menuComposition);
        m.setMenuDetail(menuDetail);
        m.setMenuDescription(menuDescription);
        m.setMenuRating(menuRating);
        m.setTags(tags);
        this.menuRepository.save(m);
    }

    public Page<Menu> getList(int page){	//MenuList 템플릿에 넘겨 페이징을 위해 사용
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("menuNo"));
        Pageable pageable = PageRequest.of(page, 30, Sort.by(sorts));
        return this.menuRepository.findAll(pageable);
    }

    public Menu getMenu(Integer menuNo){
        Optional<Menu> menu = this.menuRepository.findById(menuNo);
        if( menu.isPresent()) {
            return menu.get();
        }
        else {
            throw new DataNotFoundException("question not found");		}
    }

}
