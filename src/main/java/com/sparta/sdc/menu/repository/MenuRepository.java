package com.sparta.sdc.menu.repository;

import com.sparta.sdc.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
