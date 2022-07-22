package org.TUK.gotMenu.repository;

import org.TUK.gotMenu.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>
{
    @Query(value="SElECT * FROM comment WHERE menu_menu_no=:menuNo", nativeQuery = true)
    List<Comment> findByMenuNo(@Param("menuNo") int menuNo);
}
