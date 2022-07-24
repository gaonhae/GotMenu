package org.TUK.gotMenu.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MenuForm {

    @Size(max = 50)
    @NotEmpty(message = "메뉴 조합을 입력해주세요.")
    private String menuComposition;

    @Size(max = 1000)
    @NotEmpty(message = "메뉴 세부 정보를 입력해주세요.")
    private String menuDetail;

    //나중에 현재 글자수 알려주는 것도 추가하면 좋을 듯
    @Size(max = 1000)
    private String menuDescription;

    @Size(max = 1000)
    private String tags;


}
