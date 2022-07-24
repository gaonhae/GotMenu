package org.TUK.gotMenu.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;



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


    //추후 user와 병합
//    @ManytoOne
//    private User userNo;

}
