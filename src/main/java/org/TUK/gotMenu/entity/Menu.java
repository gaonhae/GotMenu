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
    @Column(length = 3000)
    private String Raters;
    private Integer views;

    @Column(length = 1000)
    private String tags;

    @ManyToOne
    @JoinColumn(name="writer_no")
    private User writer;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<Comment> commentList;

}
