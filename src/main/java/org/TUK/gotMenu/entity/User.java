package org.TUK.gotMenu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name="user")
public class User
{

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name="user_no", nullable = false)
    int userNo; // 기본키

    @Column(unique = true, nullable = false)
    String id; // ID 겸 닉네임
    @Column(nullable = false)
    String password; // 비밀번호
    @Column(name="join_date")
    String joinDate; // 가입일
    @Column(name="user_detail")
    String userDetail; // 유저 설명

    @OneToMany(mappedBy = "writer")
    List<Menu> menuList;
    @OneToMany(mappedBy = "writer")
    List<Comment> commentList;

    public void deletable()
    {
        menuList.stream().forEach(menu -> menu.deletable());
        commentList.stream().forEach(comment -> comment.setMenu(null));
    }

}
