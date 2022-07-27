package org.TUK.gotMenu.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.ParameterScriptAssert;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuNo;

    @Column(length = 50)
    private String menuComposition;

    @Column(columnDefinition = "TEXT", length = 5000)
    private String menuDetail;

    @Column(length = 1000)
    private String menuDescription;

    private Integer menuRating;

    @Column(length = 1000)
    private String tags;

    @ManyToOne
    private User writer;

    @OneToMany(mappedBy = "menu")
    private List<Comment> commentList;

    public void deletable()
    {
        // 모든 댓글의 유저와의 관계를 끊어라.
        commentList.stream().forEach(c -> c.setWriter(null));
    }

}
