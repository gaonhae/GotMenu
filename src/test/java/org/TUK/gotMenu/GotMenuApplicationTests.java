package org.TUK.gotMenu;

import org.TUK.gotMenu.repository.MenuRepository;
import org.TUK.gotMenu.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GotMenuApplicationTests {

	@Autowired
	private MenuService menuService;

	@Test
	void testJpa() {

		this.menuService.create("미역국, 밥", "칼로리는 500kcal", "맛있는 미역국에 밥을 말아먹으면 꿀맛!", 50, "가정식");
		this.menuService.create("된장국, 밥", "칼로리는 400kcal", "맛있는 된장국에 밥을 말아먹으면 꿀맛!", 50, "가정식");
		this.menuService.create("김치찌개, 밥", "칼로리는 600kcal", "맛있는 김치찌개에 밥을 말아먹으면 꿀맛!", 50, "가정식");
		this.menuService.create("돼지고기, 밥", "칼로리는 700kcal", "잘 구운 돼지고기랑 밥이랑 먹으면 꿀맛!", 50, "가정식");
		this.menuService.create("짜장, 탕수육", "칼로리는 700kcal", "짜장면하고 탕수육을 번갈아 먹으면 꿀맛!", 50, "외식");

	}

}
