package org.TUK.gotMenu.repository;

import org.TUK.gotMenu.entity.Menu;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("select "
            + "distinct m "
            + "from Menu m "
            + "where "
            + "   m.menuComposition like %:kw% "
            + "   or m.menuDescription like %:kw% ")
    Page<Menu> findAllByKeyword(@Param("kw") String kw, Pageable pageable);

    @Query(value = "SELECT * FROM gotmenu.menu WHERE menu_composition like %:kw%", nativeQuery = true)
    Page<Menu> findByMenuComposition(@Param("kw") String kw, Pageable pageable);

    @Query(value = "SELECT * FROM gotmenu.menu WHERE menu_detail like %:kw%", nativeQuery = true)
    Page<Menu> findByMenuDetail(@Param("kw") String kw, Pageable pageable);

    @Query(value = "SELECT * FROM gotmenu.menu WHERE tags like %:kw%", nativeQuery = true)
    Page<Menu> findByTags(@Param("kw") String kw, Pageable pageable);

    @Query(value = "SELECT * FROM gotmenu.menu  left join " +
            "gotmenu.user on gotmenu.menu.writer_no=user.user_no where id like '%:kw%';", nativeQuery = true)
    Page<Menu> findByWriterId(@Param("kw") String kw, Pageable pageable);

    Menu findByMenuNo(Integer n);

}

