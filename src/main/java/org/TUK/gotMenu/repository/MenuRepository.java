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

}
