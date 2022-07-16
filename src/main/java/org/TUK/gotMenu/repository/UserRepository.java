package org.TUK.gotMenu.repository;

import lombok.RequiredArgsConstructor;
import org.TUK.gotMenu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{


}
