package org.TUK.gotMenu.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.entity.Comment;
import org.TUK.gotMenu.entity.Menu;
import org.TUK.gotMenu.entity.User;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@RequiredArgsConstructor // Required 안에는 No가 포함되어 있다.
@Builder
public class CommentForm
{
    int commentNo;

    @Size(min=1, max=500)
    String  content;
    @Size(min=0, max=500)
    String recomment;

    String resistDate;

    // 유저 번호
    @NotNull
    int userNo;
    // 유저 ID
    String userId;
    // 메뉴 번호
    @NotNull
    int menuNo;

    // 받아온 코멘트를 엔티티로 바꿔준다.
    public Comment toEntity()
    {
        User user = new User().builder()
                .userNo(userNo)
                .id(userId)
                .build();
        Menu menu = new Menu();
        menu.setMenuNo(menuNo);

        return new Comment().builder()
                .commentNo(commentNo)
                .content(content)
                .recomment(recomment)
                .resistDate(resistDate)
                .writer(user)
                .menu(menu)
                .build();
    }



}
