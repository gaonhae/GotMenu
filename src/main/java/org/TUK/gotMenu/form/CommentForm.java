package org.TUK.gotMenu.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
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
    @Size(min=1, max=500)
    String recomment;

    int userNo;
    int menuNo;

}
