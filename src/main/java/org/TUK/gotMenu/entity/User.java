package org.TUK.gotMenu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

}
