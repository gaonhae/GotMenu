package org.TUK.gotMenu.repository;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    @Query(value="SELECT * from user WHERE id=:id")
    public User findById(@Param("id") long id);

}
