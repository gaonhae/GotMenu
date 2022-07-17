package org.TUK.gotMenu.repository;

import org.TUK.gotMenu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
