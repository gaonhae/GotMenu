package org.TUK.gotMenu.form;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.TUK.gotMenu.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;


@Data
@AllArgsConstructor
@RequiredArgsConstructor // Required 안에는 No가 포함되어 있다.
@Builder
// DTO에는 무조건 NoArgsConstructor가 있어야한다. 그래야 BindingResult를 쓸 수 있음.
public class UserForm
{
    private int userNo;

    @Size(min = 2, max = 28, message = "ID는 2자에서 28자 사이로 해야합니다.")
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String id;

    @Size(min = 8, max = 28, message = "비밀번호는 8자에서 28자 사이로 해야합니다.")
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

    @Nullable
    private String userDetail;

    private String joinDate;

    public User toEntity()
    {
        return User.builder()
                .userNo(userNo)
                .id(id)
                .password(password)
                .joinDate(joinDate)
                .userDetail(userDetail)
                .build();
    }

}
