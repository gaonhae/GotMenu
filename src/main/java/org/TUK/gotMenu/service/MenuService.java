package org.TUK.gotMenu.service;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.entity.Menu;
import org.TUK.gotMenu.repository.MenuRepository;
import org.springframework.stereotype.Service;

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

}
